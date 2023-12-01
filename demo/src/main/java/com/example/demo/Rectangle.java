package com.example.demo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Rectangle extends Shape{


    public Rectangle() {
    }


    @Override
    public String toString() {
        return "Rectangle{" +
                "id=" + id +
                ", height=" + height +
                ", width=" + width +
                ", fill='" + fill + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", index=" + index +
                ", type='" + type + '\'' +
                ", stroke='" + stroke + '\'' +
                ", strokeWidth=" + strokeWidth +
                '}';
    }

    float height;

    float width;

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    String fill;

}
