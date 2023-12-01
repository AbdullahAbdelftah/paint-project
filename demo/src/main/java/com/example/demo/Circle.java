package com.example.demo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Circle extends Shape{

    float radius;

    String fill;

    public Circle() {
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
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
