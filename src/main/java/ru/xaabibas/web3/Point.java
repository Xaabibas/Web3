package ru.xaabibas.web3;

import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

import java.io.Serializable;
import java.util.logging.Logger;

@Named
@Getter
@Setter
public class Point implements Serializable {
    private static Logger logger = Logger.getLogger(Point.class.getName());
    private double x;
    private double y;
    private double r;

    public Point() {

    }

    public Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}
