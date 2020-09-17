package com.itaycohen.controllers;

import com.itaycohen.data_layer.RemoteLibraryRepository;
import com.itaycohen.data_layer.Response;
import com.itaycohen.data_layer.dm.BookParams;
import com.itaycohen.data_layer.dm.IBook;
import com.itaycohen.views.DashBoard;
import com.itaycohen.views.MenuPanel;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

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
        dashBoard.getDeleteBookPanel().setListener(this::onDeleteBook);
    }



    private void loadBookContent(@NotNull String bookTitle, @Nullable String searchPattern) {
        BookParams[] params = new BookParams[1];
        params[0] = searchPattern != null ? new BookParams(bookTitle, searchPattern) : new BookParams(bookTitle);
        CompletableFuture<Response<IBook[]>> completable = repository.requestBooks(params);
        completable.thenAccept(response -> {
            SwingUtilities.invokeLater(() -> {
                String bookContent = response.getResponseBody()[0].getContent();
                dashBoard.getReadBookPanel().setBookContent(bookContent);
            });
        });
    }

    private void onUploadBook(String bookName, String cookContent) {

    }

    private void onDeleteBook(String bookName) {

    }

    private void loadAllBooks() {

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
        }

        @Override
        public void onDeleteClick(JButton button) {
            dashBoard.getToolBarPanel().setTitle(button.getText());
            dashBoard.setContextPanel(DashBoard.ContextPanel.DELETE_BOOK);
        }
    }
 }
