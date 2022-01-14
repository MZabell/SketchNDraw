package com.company;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Canvas extends JPanel {

    Path2D path = new Path2D.Double();
    List<Path2D> paths = new ArrayList<>();
    private final List<ButtonFunction> buttonFunctionsSide = new ArrayList<>() {{
        add(new ButtonFunction("Clear", null, e -> {
            paths.clear();
            repaint();
        }));
        add(new ButtonFunction("Undo", null, e -> {

        }));
        add(new ButtonFunction("sdaasd", null, e -> {

        }));
        add(new ButtonFunction("sdaasd", null, e -> {

        }));
        add(new ButtonFunction("sdaasd", null, e -> {

        }));
        add(new ButtonFunction("sdaasd", null, e -> {

        }));
        add(new ButtonFunction("sdaasd", null, e -> {

        }));
    }};
    Stack undoStack = new Stack();
    Stack redoStack = new Stack();
    private final List<ButtonFunction> buttonFunctionsTop = new ArrayList<>() {{
        add(new ButtonFunction("Clear", new ImageIcon("src/Icons/Clear.png"), e -> {
            paths.clear();
            undoStack.clear();
            redoStack.clear();
            repaint();
        }));
        add(new ButtonFunction("Undo", new ImageIcon("src/Icons/Undo.png"), e -> {
            if (!undoStack.empty()) {
                redoStack.push(undoStack.pop());
                paths.remove(paths.size() - 1);
                repaint();
            }
        }));
        add(new ButtonFunction("Redo", new ImageIcon("src/Icons/Redo.png"), e -> {
            if (!redoStack.empty()) {
                paths.add((Path2D) redoStack.peek());
                undoStack.push(redoStack.pop());
                repaint();
            }
        }));
        add(new ButtonFunction("sdaasd", null, e -> {

        }));
        add(new ButtonFunction("sdaasd", null, e -> {

        }));
        add(new ButtonFunction("sdaasd", null, e -> {

        }));
        add(new ButtonFunction("sdaasd", null, e -> {

        }));
    }};
    private Point2D anchor;

    public Canvas() {
        setBackground(Color.WHITE);
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                path.lineTo(e.getX(), e.getY());
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                anchor = (Point2D) e.getPoint().clone();
                path = new GeneralPath();
                path.moveTo(anchor.getX(), anchor.getY());
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (path != null) {
                    paths.add(path);
                    undoStack.push(path);
                    path = null;
                }
                repaint();
            }
        });
    }

    public List<ButtonFunction> getButtonFunctionsTop() {
        return buttonFunctionsTop;
    }

    public List<ButtonFunction> getButtonFunctionsSide() {
        return buttonFunctionsSide;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        if (path != null)
            g2d.draw(path);

        for (Path2D p : paths)
            g2d.draw(p);
    }
}
