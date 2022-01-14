package com.company;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class GraphicsPanel extends JPanel {

    private final GridBagConstraints c;

    public GraphicsPanel(Canvas canvas) {
        setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        createMenus();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 99;
        c.gridheight = 99;
        add(canvas, c);
    }

    private void createMenus() {
        for (int i = 0; i < 20; i++) {
            add(new MenuButton(), c);
            c.gridx++;
            i++;
        }
        c.gridx = 0;
        c.gridy = 1;
        for (int h = 0; h < 20; h++) {
            add(new MenuButton(), c);
            c.gridy++;
            h++;
        }
    }
}
