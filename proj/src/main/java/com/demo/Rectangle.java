package com.demo;

public class Rectangle {
    private double length;
    private double height;

    public Rectangle(double length, double height) {
        if (length <= 0 || height <= 0)
            throw new IllegalArgumentException(
                    String.format("Not correct values length=%s, height=%s", length, height));

        this.length = length;
        this.height = height;
    }

    public double area() {
        return length*height;
    }

    public double perimeter() {
        return 2*(length+height);
    }

}
