package com.company;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;

public class ButtonFunction {

    String toolTip;
    ActionListener actionListener;
    ImageIcon imageIcon;

    public ButtonFunction(String toolTip, ImageIcon imageIcon, ActionListener actionListener) {
        this.toolTip = toolTip;
        this.actionListener = actionListener;
        this.imageIcon = imageIcon;
    }
}
