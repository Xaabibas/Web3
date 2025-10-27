package ru.xaabibas.web3;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.LinkedList;
import java.util.List;

@Named("keeper")
@ApplicationScoped
public class AttemptKeeper {
    List<Attempt> attempts = new LinkedList<>();

    public void add(Attempt attempt) {
        System.out.println(attempt.toString());
        attempts.add(attempt);
    }
}
