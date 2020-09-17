package com.itaycohen.views;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ReadBookPanel extends JPanel {

    private final JTextField bookTitleTextField = new JTextField(10);
    private final JTextArea bookContentTextArea = new JTextArea();
    private final JTextField searchTextField = new JTextField(20);
    private final JCheckBox searchCheckBox = new JCheckBox();
    private final JLabel searchLabel = new JLabel("Search pattern: ");
    private Listener listener;

    public ReadBookPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        /* First Row */
        gbc.gridy = 0;

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        this.add(new JLabel("Book title: "));

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.weightx = 1;
        this.add(bookTitleTextField, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.weightx = 0;
        JButton loadBookBtn = new JButton("Load");
        this.add(loadBookBtn, gbc);

        gbc.gridx = 3;
        searchCheckBox.addActionListener(e -> {
            boolean shouldUseSearch = searchCheckBox.isSelected();
            searchLabel.setEnabled(shouldUseSearch);
            searchTextField.setEnabled(shouldUseSearch);
        });
        this.add(searchCheckBox);

        gbc.gridx = 4;
        this.add(new JLabel("use search "), gbc);



        /*Second Row */
        gbc.gridy = 1;

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        searchLabel.setEnabled(false);
        this.add(searchLabel, gbc);

        gbc.gridx = 1;
        searchTextField.setEnabled(false);
        this.add(searchTextField, gbc);


        /* Third row*/
        gbc.gridy = 2;

        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.BOTH;
        bookContentTextArea.setLineWrap(true);
        bookContentTextArea.setWrapStyleWord(true);
        bookContentTextArea.setEditable(false);
        this.add(new JScrollPane(bookContentTextArea), gbc);

        loadBookBtn.addActionListener(e -> {
            if (listener != null) listener.onLoadClick(bookTitleTextField.getText(), searchCheckBox.isSelected() ? searchTextField.getText() : null);
        });
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setBookContent(String text) {
        bookContentTextArea.setText(text);
    }

    public interface Listener {
        void onLoadClick(@NotNull String bookTitle, @Nullable String searchPattern);
    }
}
