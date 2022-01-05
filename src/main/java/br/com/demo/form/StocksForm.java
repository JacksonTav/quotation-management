package br.com.demo.form;

import br.com.demo.model.Stocks;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Setter
@Getter
public class StocksForm {
    @NotNull @NotEmpty
    private String stockId;

    public Stocks returnNew() {
        return new Stocks(stockId);
    }

}
