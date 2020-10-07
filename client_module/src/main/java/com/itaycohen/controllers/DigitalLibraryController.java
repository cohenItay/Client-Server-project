package com.itaycohen.controllers;

import com.itaycohen.data_layer.RemoteLibraryRepository;
import com.itaycohen.data_layer.Response;
import com.itaycohen.dm.BookParams;
import com.itaycohen.dm.BookWithSearch;
import com.itaycohen.dm.IBook;
import com.itaycohen.dm.SearchResult;
import com.itaycohen.views.DashBoard;
import com.itaycohen.views.DeleteBookPanel;
import com.itaycohen.views.MenuPanel;
import com.itaycohen.views.ReadBookPanel;
import com.itaycohen.views.UploadBookPanel;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class DigitalLibraryController {

    private final DashBoard dashBoard;
    private final RemoteLibraryRepository repository;

    public DigitalLibraryController(
            @NotNull DashBoard dashBoard,
            @NotNull RemoteLibraryRepository repo
    ) {
        Objects.requireNonNull(dashBoard);
        Objects.requireNonNull(repo);
        this.dashBoard = dashBoard;
        this.repository = repo;

        SwingUtilities.invokeLater(this::initGUI);
    }

    private void initGUI() {
        dashBoard.init();
        dashBoard.getToolBarPanel().setTitle("Read Book");
        dashBoard.getMenuPanel().setTitle("My library");
        dashBoard.getMenuPanel().setListener(new MenuItemClickImpl());

        dashBoard.getReadBookPanel().setListener(this::loadBookContent);
        dashBoard.getUploadBookPanel().setListener(this::onUploadBook);
        dashBoard.getDeleteBookPanel().setListener(this::onDeleteBookClick);
    }



    private void loadBookContent(@NotNull String bookTitle, @Nullable String searchPattern) {
        if (bookTitle.isEmpty())
            return;

        dashBoard.getReadBookPanel().setState(ReadBookPanel.State.LOADING);
        BookParams[] params = new BookParams[1];
        params[0] = searchPattern != null ? new BookParams(bookTitle, searchPattern) : new BookParams(bookTitle);
        CompletableFuture<Response<IBook[]>> completable = repository.requestBooks(params);
        completable.thenAccept(response -> SwingUtilities.invokeLater(() -> {
            String responseState = response.getHeaders().get(Response.Header.Keys.RESPONSE_CODE);
            boolean isOK = responseState.equals(Response.Header.Values.OK);
            dashBoard.getReadBookPanel().setState(isOK ? ReadBookPanel.State.IDLE : ReadBookPanel.State.NO_BOOK);
            displayBook(response);
        }));
    }

    private void onUploadBook(String bookName, String bookContent) {
        dashBoard.getUploadBookPanel().setState(UploadBookPanel.State.UPLOADING);
        BookParams[] params = new BookParams[1];
        params[0] = new BookParams(bookName, null, bookContent);
        CompletableFuture<Response<Void>> completableFuture = repository.uploadBooks(params);
        completableFuture.thenAccept(response -> SwingUtilities.invokeLater(() -> {
                    String responseState = response.getHeaders().get(Response.Header.Keys.RESPONSE_CODE);
                    boolean isOK = responseState.equals(Response.Header.Values.OK);
                    dashBoard.getUploadBookPanel().setState(isOK ? UploadBookPanel.State.SAVED : UploadBookPanel.State.ERROR);
                }
        ));
    }

    private void onDeleteBookClick(String bookName) {
        dashBoard.getDeleteBookPanel().setState(DeleteBookPanel.State.DELETING);
        BookParams[] params = new BookParams[1];
        params[0] = new BookParams(bookName);
        CompletableFuture<Response<Void>> completableFuture = repository.deleteBooks(params);
        completableFuture.thenAccept(response -> SwingUtilities.invokeLater(() -> {
                    String responseState = response.getHeaders().get(Response.Header.Keys.RESPONSE_CODE);
                    boolean isOK = responseState.equals(Response.Header.Values.OK);
                    dashBoard.getDeleteBookPanel().setState(isOK ? DeleteBookPanel.State.DELETED : DeleteBookPanel.State.ERROR);
                    refreshDeletePanelBooksList();
                }
        ));
    }

    private void displayBook(Response<IBook[]> response) {
        IBook[] body = response.getResponseBody();
        if (body == null || body.length < 1)
            return;

        IBook book = body[0];
        if (book == null)
            return;

        if (book instanceof BookWithSearch) {
            SearchResult searchResult = ((BookWithSearch)book).getSearchResult();
            if (searchResult != null) {
                dashBoard.getReadBookPanel().setBookContentWithHighlight(
                        book.getContent(),
                        searchResult.getPatternOccurrences(),
                        searchResult.getSearchParams().getSearchPattern().length()
                );
            } else {
                dashBoard.getReadBookPanel().setBookContent(book.getContent());
            }
        } else {
            dashBoard.getReadBookPanel().setBookContent(book.getContent());
        }
    }

    private class MenuItemClickImpl implements MenuPanel.Listener {
        @Override
        public void onReadClick(JButton button) {
            dashBoard.getToolBarPanel().setTitle(button.getText());
            dashBoard.setContextPanel(DashBoard.ContextPanel.READ_BOOK);
        }

        @Override
        public void onUploadClick(JButton button) {
            dashBoard.getToolBarPanel().setTitle(button.getText());
            dashBoard.setContextPanel(DashBoard.ContextPanel.UPLOAD_BOOK);
            dashBoard.getUploadBookPanel().setState(UploadBookPanel.State.IDLE);
        }

        @Override
        public void onDeleteClick(JButton button) {
            dashBoard.getToolBarPanel().setTitle(button.getText());
            dashBoard.setContextPanel(DashBoard.ContextPanel.DELETE_BOOK);
            dashBoard.getDeleteBookPanel().setState(DeleteBookPanel.State.IDLE);
            refreshDeletePanelBooksList();

        }
    }

    private void refreshDeletePanelBooksList() {
        repository.peekAllBooks().thenAccept(response ->
                SwingUtilities.invokeLater(() ->
                        dashBoard.getDeleteBookPanel().setListModel(convertToDefaultListModel(response))
                )
        );
    }

    private DefaultListModel<String> convertToDefaultListModel(Response<IBook[]> response) {
        DefaultListModel<String> out = new DefaultListModel<>();
        IBook[] booksNames = response.getResponseBody();
        if (booksNames == null || booksNames.length < 1)
            return out;

        for (IBook book : booksNames) {
            out.addElement(book.getTitle());
        }
        return out;
    }
}
