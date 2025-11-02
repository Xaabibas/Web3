package ru.xaabibas.web3;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Getter;
import org.primefaces.PrimeFaces;

import java.util.LinkedList;
import java.util.List;

@Named("keeper")
@ApplicationScoped
@Getter
public class AttemptKeeper {
    private List<Attempt> attempts = new LinkedList<>();

    public void add(Attempt attempt) {
        attempt.submit();
        attempts.add(attempt);
    }

    public void clear() {
        attempts.clear();
    }

    public void addFromJS() {
        String x = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("x");
        String y = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("y");
        String r = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("r");

        Point point = new Point(
                Double.parseDouble(x),
                Double.parseDouble(y),
                Double.parseDouble(r)
        );
        Attempt attempt = new Attempt();
        attempt.setPoint(point);
        attempt.submit();
        attempts.add(attempt);

        PrimeFaces.current().ajax().addCallbackParam("result", attempt.isResult());
    }
}
