package ru.xaabibas.web3;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.primefaces.PrimeFaces;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="attempts")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Attempt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Transient
    private Checker checker = new Checker();

    @Embedded
    private Point point = new Point();

    @Column(name="result", nullable = false)
    private boolean result;

    @JsonIgnore
    @Column(name="start", nullable = false)
    private String start;

    @JsonIgnore
    @Column(name="workTime", nullable = false)
    private long workTime;

    public void getR() {
        PrimeFaces.current().ajax().addCallbackParam("r", this.point.getR());
    }
}
