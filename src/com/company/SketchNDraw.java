package com.company;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                canvas.getGraphics().fillOval(MouseInfo.getPointerInfo().getLocation().x - 68, MouseInfo.getPointerInfo().getLocation().y - 58, 5, 5);
            }
        });
    }

    public static void main(String[] args) {
	new SketchNDraw();
    }
}
