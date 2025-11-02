package ru.xaabibas.web3;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.primefaces.PrimeFaces;


import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Named("attempt")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequestScoped
public class Attempt implements Serializable {
    @JsonIgnore
    private Checker checker = new Checker();
    private Point point = new Point();
    private boolean result;
    @JsonIgnore
    private String start;
    @JsonIgnore
    private long workTime;

    public void submit() {
        long workStart = System.nanoTime();
        setCurrentStart();
        check();
        long workEnd = System.nanoTime();
        workTime = (workEnd - workStart) / 1_000;
    }

    private void setCurrentStart() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        start = now.format(formatter);
    }

    private void check() {
        result = checker.check(point);
    }

    public boolean isResult() {
        return result;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void getR() {
        PrimeFaces.current().ajax().addCallbackParam("r", this.point.getR());
    }

    public void update() {
        submit();
        PrimeFaces.current().ajax().addCallbackParam("x", this.point.getX());
        PrimeFaces.current().ajax().addCallbackParam("y", this.point.getY());
        PrimeFaces.current().ajax().addCallbackParam("result", this.result);
    }
}
