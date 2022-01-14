package com.company;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuButton extends JButton implements ActionListener {

    public MenuButton() {
        setText("Test");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
