package com.company;

import javax.swing.JFrame;

public class SketchNDraw extends JFrame {

    private final GraphicsPanel graphicsPanel;
    private final Canvas canvas = new Canvas();

    public SketchNDraw() {
        graphicsPanel = new GraphicsPanel(canvas);

        setTitle("SketchNDraw");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().add(graphicsPanel);
        pack();

    }

    public static void main(String[] args) {
        new SketchNDraw();
    }
}
