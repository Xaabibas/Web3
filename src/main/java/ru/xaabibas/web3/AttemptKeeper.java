package ru.xaabibas.web3;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Named("keeper")
@ApplicationScoped
@Getter
public class AttemptKeeper {
    private List<Attempt> attempts = new LinkedList<>();

    public void add(Attempt attempt) {
        attempts.add(attempt);
    }
}
