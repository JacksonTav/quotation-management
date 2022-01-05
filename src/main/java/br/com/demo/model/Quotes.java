package br.com.demo.model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
public class Quotes {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stockId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private String stockId;
    private Date date;
    private Integer value;

    public Quotes() {}

    public Quotes(String stockId, Date date, Integer value) {
        this.stockId = stockId;
        this.date = date;
        this.value = value;
    }
}
