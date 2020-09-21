package com.itaycohen.views;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DashBoard extends JFrame {

    private MenuPanel menuPanel = new MenuPanel();
    private ToolbarPanel toolBarPanel = new ToolbarPanel();

    private ContextPanel contextPanelEnum = ContextPanel.READ_BOOK;

    private JPanel contextPanel = new JPanel();
    private CardLayout contextPanelCardLayout = new CardLayout();
    private ReadBookPanel readBookPanel = new ReadBookPanel();
    private UploadBookPanel uploadBookPanel = new UploadBookPanel();
    private DeleteBookPanel deleteBookPanel = new DeleteBookPanel();

    public void init() {
        setVisible(true);
        setLayout(new BorderLayout());
        setSize(640, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contextPanel.setLayout(contextPanelCardLayout);
        contextPanel.add(readBookPanel, ContextPanel.READ_BOOK.label);
        contextPanel.add(uploadBookPanel, ContextPanel.UPLOAD_BOOK.label);
        contextPanel.add(deleteBookPanel, ContextPanel.DELETE_BOOK.label);

        add(toolBarPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.WEST);
        add(contextPanel, BorderLayout.CENTER);
    }

    public void setContextPanel(ContextPanel contextPanelEnum) {
        if (contextPanelEnum == this.contextPanelEnum)
            return;

        contextPanelCardLayout.show(contextPanel, contextPanelEnum.label);
        this.contextPanelEnum = contextPanelEnum;
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public ToolbarPanel getToolBarPanel() {
        return toolBarPanel;
    }

    public ReadBookPanel getReadBookPanel() {
        return readBookPanel;
    }

    public UploadBookPanel getUploadBookPanel() {
        return uploadBookPanel;
    }

    public DeleteBookPanel getDeleteBookPanel() {
        return deleteBookPanel;
    }

    public enum ContextPanel {
        READ_BOOK("READ_BOOK"),
        UPLOAD_BOOK("UPLOAD_BOOK"),
        DELETE_BOOK("DELETE_BOOK");

        public final String label;

        private ContextPanel(String label) {
            this.label = label;
        }
    }
}
