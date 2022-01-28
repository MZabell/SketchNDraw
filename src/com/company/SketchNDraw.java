package com.company;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SketchNDraw extends JFrame {

    private final GraphicsPanel graphicsPanel;
    public final Canvas canvas = new Canvas();

    public SketchNDraw() {
        graphicsPanel = new GraphicsPanel(canvas);

        setTitle("SketchNDraw");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().add(graphicsPanel);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem menuItem1 = new JMenuItem("Save Project");
        JMenuItem menuItem2 = new JMenuItem("Save Project and Quit");
        JMenuItem menuItem3 = new JMenuItem("Export as PNG");
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileSerialization.saveData(canvas);
                System.out.println("File Saved");
            }
        });
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        pack();

    }

    public static void main(String[] args) {
        new SketchNDraw();
    }
}
