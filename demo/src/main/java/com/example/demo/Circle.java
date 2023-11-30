package com.example.demo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Circle extends Shape{

    int id;
    int radius;
    String fill;
    String stroke;
    int strokeWidth;

    public void setId(int id) {
        this.id = id;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int getId() {
        return id;
    }

    public int getRadius() {
        return radius;
    }

    public String getFill() {
        return fill;
    }

    public String getStroke() {
        return stroke;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "id=" + id +
                ", radius=" + radius +
                ", fill='" + fill + '\'' +
                ", stroke='" + stroke + '\'' +
                ", strokeWidth=" + strokeWidth +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
