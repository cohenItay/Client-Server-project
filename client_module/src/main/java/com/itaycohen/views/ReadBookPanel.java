package com.itaycohen.views;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class ReadBookPanel extends JPanel {

    private final JTextField bookTitleTextField = new JTextField(10);
    private final JTextArea bookContentTextArea = new JTextArea();
    private final JTextField searchTextField = new JTextField(20);
    private final JCheckBox searchCheckBox = new JCheckBox();
    private final JLabel searchLabel = new JLabel("Search pattern: ");
    private Listener listener;
    private State currentState = State.IDLE;

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
        setBookContentWithHighlight(text, null, null);
    }

    public void setBookContentWithHighlight(String text, @Nullable List<Integer> indices, @Nullable Integer patternLength) {
        bookContentTextArea.setText(text);
        if (indices != null && !indices.isEmpty()) {
            Highlighter highlighter = bookContentTextArea.getHighlighter();
            Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
            for (Integer index : indices) {
                try {
                    highlighter.addHighlight(index, index+patternLength, painter);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setState(State newState) {
        String content = bookContentTextArea.getText();

        if (currentState != State.IDLE)
            content = content.replace(currentState.label, newState.label);
        else
            content  = newState.label + content;

        currentState = newState;
        bookContentTextArea.setText(content);
    }

    public enum State {
        LOADING("Loading...\n===========\n\n"),
        NO_BOOK("No such book\n===========\n\n"),
        IDLE("");

        public final String label;

        private State(String label) {
            this.label = label;
        }
    }

    public interface Listener {
        void onLoadClick(@NotNull String bookTitle, @Nullable String searchPattern);
    }
}
