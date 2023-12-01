package com.example.demo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Square extends Shape{
    float width;
    float height;
    String fill;

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public Square() {
    }



    @Override
    public String toString() {
        return "Square{" +
                "width=" + width +
                ", height=" + height +
                ", fill='" + fill + '\'' +
                ", stroke='" + stroke + '\'' +
                ", strokeWidth=" + strokeWidth +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

}
