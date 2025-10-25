package ru.xaabibas.web3;

public class Checker {
    public boolean check(Point point) {
        double x = point.getX();
        double y = point.getY();
        double r = point.getR();

        return checkBox(x, y, r);
    }

    public boolean checkBox(double x, double y, double r) {
        if (x >= 0 && y >= 0) {
            return x*x + y*y <= r*r;
        }
        if (x >= 0 && y < 0) {
            return y >= 2 * x - r;
        }
        if (x < 0 && y <= 0) {
            return x >= -r && y >= -r;
        }
        return false;
    }
}
