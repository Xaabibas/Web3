package ru.xaabibas.web3;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.io.Serializable;

@Named("attempt")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequestScoped
public class Attempt implements Serializable {
    private Point point = new Point();
    private boolean result;
    private String start;
    private long workTime;
}
