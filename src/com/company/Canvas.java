package com.company;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
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

    private final List<StrokeProperties> strokeProperties = new ArrayList<>();
    Path2D path = new Path2D.Double();
    Stack<Path2D> pathStack = new Stack<>();
    Stack<Path2D> redoStack = new Stack<>();
    Color strokeColor = Color.BLACK;
    float strokeWidth = 1;
    int strokeMode = 0;
    SketchNDraw sketchNDraw;
    private final List<ButtonFunction> buttonFunctionsTop = new ArrayList<>() {{
        add(new ButtonFunction("Clear", new ImageIcon("src/Icons/Clear.png"), e -> {
            pathStack.clear();
            redoStack.clear();
            strokeProperties.clear();
            sketchNDraw.setOpenFile(null);
            setBackground(Color.WHITE);
            repaint();
        }));
        add(new ButtonFunction("Undo", new ImageIcon("src/Icons/Undo.png"), e -> {
            if (!pathStack.empty()) {
                redoStack.push(pathStack.pop());
                repaint();
            }
        }));
        add(new ButtonFunction("Redo", new ImageIcon("src/Icons/Redo.png"), e -> {
            if (!redoStack.empty()) {
                pathStack.push(redoStack.pop());
                repaint();
            }
        }));
        add(new ButtonFunction("Color Chooser", new ImageIcon("src/Icons/ColorChooser.png"), e -> strokeColor = JColorChooser.showDialog(Canvas.this, "Choose your color", Color.WHITE)));
        add(new ButtonFunction("Stroke Width", new ImageIcon("src/Icons/StrokeWidth.png"), e -> strokeWidth = Float.parseFloat(JOptionPane.showInputDialog(Canvas.this, "Enter stroke width"))));
        add(new ButtonFunction("Fill", new ImageIcon("src/Icons/Fill.png"), e -> {
            if (strokeMode != 1)
                strokeMode = 1;
            else strokeMode = 0;
        }));
        add(new ButtonFunction("Fill all of Color", new ImageIcon("src/Icons/FillAll.png"), e -> {
            if (strokeMode != 2)
                strokeMode = 2;
            else strokeMode = 0;
        }));
        add(new ButtonFunction("Eraser", new ImageIcon("src/Icons/Eraser.png"), e -> strokeColor = getBackground()));
    }};
    private Point2D anchor;

    public Canvas(SketchNDraw sketchNDraw) {
        this.sketchNDraw = sketchNDraw;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1250, 500));
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (strokeMode == 0) {
                    path.lineTo(e.getX(), e.getY());
                }
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (strokeMode == 0) {
                    anchor = (Point2D) e.getPoint().clone();
                    path = new GeneralPath();
                    path.moveTo(anchor.getX(), anchor.getY());
                }

                if (strokeMode == 1) {
                    for (StrokeProperties p : strokeProperties) {
                        if (p.getPath().contains(e.getX(), e.getY())) {
                            p.setColor(strokeColor);
                        } else setBackground(strokeColor);
                    }
                }

                if (strokeMode == 2) {
                    Color sameColor;
                    for (StrokeProperties p : strokeProperties) {
                        if (p.getPath().contains(e.getX(), e.getY())) {
                            sameColor = p.getColor();
                            for (StrokeProperties h : strokeProperties) {
                                if (h.getColor() == sameColor) {
                                    h.setColor(strokeColor);
                                }
                            }
                        }
                    }
                }

                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (strokeMode == 0) {
                    if (path != null) {
                        strokeProperties.add(new StrokeProperties(path, strokeColor, strokeWidth));
                        pathStack.push(path);
                        path = null;
                    }
                }
                repaint();
            }
        });
    }

    public List<ButtonFunction> getButtonFunctionsTop() {
        return buttonFunctionsTop;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        if (sketchNDraw.getOpenFile() != null) {
            g2d.drawImage(FileSerialization.openPNG(sketchNDraw.getOpenFile(), this), 0, 0, this);
        }

        int index = 0;

        for (Path2D p : pathStack) {
            g2d.setStroke(new BasicStroke(strokeProperties.get(index).getWidth()));
            g2d.setColor(strokeProperties.get(index).getColor());
            g2d.draw(p);
            index++;
        }

        if (path != null) {
            g2d.setStroke(new BasicStroke(strokeWidth));
            g2d.setColor(strokeColor);
            g2d.draw(path);
        }
    }
}

