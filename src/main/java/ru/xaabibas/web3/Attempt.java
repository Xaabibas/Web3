package ru.xaabibas.web3;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


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
    private Checker checker = new Checker();
    private Point point = new Point();
    private boolean result;
    private String start;
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
}
