package br.com.demo.form;

import br.com.demo.model.Quotes;
import br.com.demo.model.Stocks;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class QuotesForm {
    private String stockId;
    private Date registerDate;
    private int value;

    public QuotesForm() {}

    public QuotesForm(String stockId, Date registerDate, int value) {
        this.stockId = stockId;
        this.registerDate = registerDate;
        this.value = value;
    }

    public Quotes returnNew() {
        return new Quotes(stockId, registerDate, value);
    }
}
