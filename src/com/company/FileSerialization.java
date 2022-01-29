package com.company;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FileSerialization {
    public static void saveProject(Canvas canvas) {
        try {
            FileOutputStream fos = new FileOutputStream("save");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(canvas);
            oos.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void savePNG(Canvas canvas, String pathname) {
        BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        canvas.paint(g);
        try {
            ImageIO.write(image, "png", new File(pathname + ".png"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static BufferedImage openPNG(File file, Canvas canvas) {
        BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        try {
            image = ImageIO.read(file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return image;
    }
}
