package com.itaycohen.views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class MenuPanel extends JPanel {

    private final JLabel title = new JLabel();
    private Listener listener;

    public MenuPanel() {
        setBackground(new Color(163,163,194));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));


        JButton b1 = new JButton("Read book");
        JButton b2 = new JButton("Upload book");
        JButton b3 = new JButton("Delete book");

        b1.addActionListener(e -> listener.onReadClick(b1));
        b2.addActionListener(e -> listener.onUploadClick(b2));
        b3.addActionListener(e -> listener.onDeleteClick(b3));
        setListener(listener);

        add(b1);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(b2);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(b3);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onReadClick(JButton button);
        void onUploadClick(JButton button);
        void onDeleteClick(JButton button);
    }
}
