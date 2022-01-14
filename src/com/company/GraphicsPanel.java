package com.company;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class GraphicsPanel extends JPanel {

    private final GridBagConstraints c;

    public GraphicsPanel(Canvas canvas) {
        setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        createMenus(canvas);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 99;
        c.gridheight = 99;
        add(canvas, c);
    }

    private void createMenus(Canvas canvas) {
        var toolbarTop = new JToolBar();
        var toolbarSide = new JToolBar();
        toolbarSide.setOrientation(SwingConstants.VERTICAL);
        for (ButtonFunction buttonFunction : canvas.getButtonFunctionsTop()) {
            JButton menuButton = new JButton();
            menuButton.addActionListener(buttonFunction.actionListener);
            menuButton.setToolTipText(buttonFunction.toolTip);
            menuButton.setIcon(buttonFunction.imageIcon);
            toolbarTop.add(menuButton);
            add(toolbarTop, c);
            c.gridx++;
        }
        c.gridx = 0;
        c.gridy = 1;
        for (ButtonFunction buttonFunction : canvas.getButtonFunctionsSide()) {
            JButton menuButton = new JButton();
            menuButton.addActionListener(buttonFunction.actionListener);
            menuButton.setToolTipText(buttonFunction.toolTip);
            menuButton.setText(buttonFunction.toolTip);
            toolbarSide.add(menuButton);
            add(toolbarSide, c);
            c.gridy++;
        }
    }
}
