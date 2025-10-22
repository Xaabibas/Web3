package ru.xaabibas.web3;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class Point {
    private double x = 1;
    private double y = 1;
    private double r = 1;

    public Point() {
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(double r) {
        this.r = r;
    }
}
