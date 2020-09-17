package com.itaycohen.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UploadBookPanel extends JPanel {

    private final JTextField bookTitleTextField = new JTextField(10);
    private final JTextArea bookContentTextArea = new JTextArea();
    private Listener listener;

    public UploadBookPanel() {
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

        /* Second row*/
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.gridx = 0;
        this.add(new JLabel("Book content:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        JButton saveButton = new JButton("Upload");
        saveButton.addActionListener(e -> {
            if (listener != null) listener.onUpload(bookTitleTextField.getText(), bookContentTextArea.getText());
        });
        this.add(saveButton, gbc);

        /* Third row*/
        gbc.gridy = 2;

        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        bookContentTextArea.setLineWrap(true);
        bookContentTextArea.setWrapStyleWord(true);
        this.add(new JScrollPane(bookContentTextArea), gbc);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {

        void onUpload(String bookTitle, String bookContent);
    }
}
