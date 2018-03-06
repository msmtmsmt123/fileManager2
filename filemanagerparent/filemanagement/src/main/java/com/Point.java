package com;

public class Point {
    int pointX;
    int pointY;

    @Override
    public String toString() {
        return "com.Point{" +
                "pointX=" + pointX +
                ", pointY=" + pointY +
                '}';
    }

    public int getPointX() {
        return pointX;
    }

    public void setPointX(int pointX) {
        this.pointX = pointX;
    }

    public int getPointY() {
        return pointY;
    }

    public void setPointY(int pointY) {
        this.pointY = pointY;
    }
}