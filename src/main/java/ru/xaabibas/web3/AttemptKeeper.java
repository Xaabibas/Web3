package ru.xaabibas.web3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.primefaces.PrimeFaces;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AttemptKeeper {
    @Resource
    private UserTransaction userTransaction;

    @PersistenceContext
    private EntityManager entityManager;

    private final Checker checker = new Checker();
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Attempt> attempts;

    @Transactional
    public void add(Attempt attempt) {
        try {
            userTransaction.begin();

            processAttempt(attempt);
            entityManager.merge(attempt);
            attempts.add(attempt);

            userTransaction.commit();
        } catch (Exception e) {
            try {
                if (userTransaction.getStatus() == jakarta.transaction.Status.STATUS_ACTIVE ||
                        userTransaction.getStatus() == jakarta.transaction.Status.STATUS_MARKED_ROLLBACK) {
                    userTransaction.rollback();
                }
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Transactional
    public void clear() {
        try {
            userTransaction.begin();

            entityManager.createNativeQuery("TRUNCATE TABLE attempts").executeUpdate();
            attempts.clear();

            userTransaction.commit();
        } catch (Exception e) {
            try {
                if (userTransaction.getStatus() == jakarta.transaction.Status.STATUS_ACTIVE ||
                        userTransaction.getStatus() == jakarta.transaction.Status.STATUS_MARKED_ROLLBACK) {
                    userTransaction.rollback();
                }
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void processAttempt(Attempt attempt) {
        long workStart = System.nanoTime();
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        attempt.setStart(now.format(formatter));
        attempt.setResult(checker.check(attempt));
        long workEnd = System.nanoTime();
        attempt.setWorkTime((workEnd - workStart) / 1_000);
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

        add(attempt);

        PrimeFaces.current().ajax().addCallbackParam("result", attempt.isResult());
    }

    public String initList() throws JsonProcessingException {
        attempts = selectAttemptsList();
        return mapper.writeValueAsString(
                attempts
        );
    }

    public void sendLastAttempt() {
        Attempt attempt = attempts.get(attempts.size() - 1);
        PrimeFaces.current().ajax().addCallbackParam("x", attempt.getPoint().getX());
        PrimeFaces.current().ajax().addCallbackParam("y", attempt.getPoint().getY());
        PrimeFaces.current().ajax().addCallbackParam("result", attempt.isResult());
    }

    public List<Attempt> selectAttemptsList() {
        return entityManager.createQuery("SELECT a FROM Attempt a", Attempt.class).getResultList();
    }

    public List<Attempt> getAttempts() {
        if (attempts == null) {
            attempts = selectAttemptsList();
        }
        return attempts;
    }
}
