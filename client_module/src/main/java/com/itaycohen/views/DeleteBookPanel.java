package com.itaycohen.views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class DeleteBookPanel extends JPanel {

    private final JList<String> booksJList = new JList<>();
    private final JLabel stateLabel = new JLabel("");
    private Listener listener;

    public DeleteBookPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        /* First Column */
        gbc.gridx = 0;
        gbc.gridheight = 2;
        gbc.weighty = 1;
        gbc.weightx = 5;
        gbc.fill = GridBagConstraints.BOTH;
        booksJList.setBorder(BorderFactory.createLineBorder(new Color(118, 30, 138)));
        this.add(booksJList, gbc);

        /* Second Column */
        gbc.gridx = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.SOUTH;
        JButton deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(e -> {
            String bookName = booksJList.getSelectedValue();
            if (bookName != null && !bookName.isEmpty() && listener != null)
                listener.onDelete(bookName);
        });
        this.add(deleteBtn, gbc);

        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        this.add(stateLabel, gbc);

    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setListModel(DefaultListModel<String> model) {
        booksJList.setModel(model);
    }

    public void setState(State newState) {
        stateLabel.setText(newState.label);
    }

    public enum State {
        DELETING("Deleting..."),
        DELETED("Deleted"),
        ERROR("Error"),
        IDLE("");

        public final String label;

        private State(String label) {
            this.label = label;
        }
    }

    public interface Listener {
        void onDelete(String bookName);
    }
}
