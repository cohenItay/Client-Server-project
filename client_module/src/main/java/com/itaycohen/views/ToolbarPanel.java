package com.itaycohen.views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ToolbarPanel extends JPanel {

    private final JLabel title = new JLabel();

    public ToolbarPanel() {
        setBackground(new Color(102,102,153));
        setBorder(BorderFactory.createEtchedBorder());
        setVisible(true);
        //setLayout(new BorderLayout());
        add(title, BorderLayout.CENTER);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }
}
