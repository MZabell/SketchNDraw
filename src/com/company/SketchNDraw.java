package com.company;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.MouseInfo;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.nio.file.Path;

public class SketchNDraw extends JFrame {

    private final GraphicsPanel graphicsPanel;
    private Canvas canvas = new Canvas();

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
