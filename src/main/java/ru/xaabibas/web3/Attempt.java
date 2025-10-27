package ru.xaabibas.web3;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;

@Named("attempt")
@Getter
@Setter
@NoArgsConstructor
@SessionScoped
public class Attempt implements Serializable {
    private Point point = new Point();
    private boolean result;
    private String start;
    private long workTime;
}
