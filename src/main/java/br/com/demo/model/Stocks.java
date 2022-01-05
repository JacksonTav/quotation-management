package br.com.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import javax.persistence.OneToMany;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Stocks {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String stockId;

    public Stocks() {}

    public Stocks(String stockId) {
        this.stockId = stockId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Stocks other = (Stocks) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }



}
