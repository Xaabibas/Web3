package ru.xaabibas.web3;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@ToString
@Embeddable
public class Point implements Serializable {
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
