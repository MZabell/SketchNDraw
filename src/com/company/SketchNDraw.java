package com.company;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class SketchNDraw extends JFrame {

    public final Canvas canvas = new Canvas(this);
    private final GraphicsPanel graphicsPanel;
    private File openFile;

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
        JMenuItem menuItem4 = new JMenuItem("Open PNG in Project");
        menuItem1.addActionListener(e -> {
            FileSerialization.saveProject(canvas);
            System.out.println("File Saved");
        });
        menuItem3.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("PNG", "png"));
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (file.exists()) {
                    int response = JOptionPane.showConfirmDialog(null, "File already exists, overwrite?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    switch (response) {
                        case 0:
                            FileSerialization.savePNG(canvas, file.getAbsolutePath());
                            break;
                        case 1:
                            break;
                    }
                } else FileSerialization.savePNG(canvas, file.getAbsolutePath());
            }
        });
        menuItem4.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("PNG", "png"));
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                openFile = fileChooser.getSelectedFile();
                canvas.repaint();
            }
        });
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menu.add(menuItem4);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        pack();

    }

    public static void main(String[] args) {
        new SketchNDraw();
    }

    public File getOpenFile() {
        return openFile;
    }

    public void setOpenFile(File openFile) {
        this.openFile = openFile;
    }
}
