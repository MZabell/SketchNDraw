package com.company;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileSerialization {

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
