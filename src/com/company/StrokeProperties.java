package com.company;

import java.awt.Color;
import java.awt.geom.Path2D;

public class StrokeProperties {

    private final Path2D path;
    private final float width;
    private Color color;

    public StrokeProperties(Path2D path, Color color, float width) {
        this.path = path;
        this.color = color;
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getWidth() {
        return width;
    }
}