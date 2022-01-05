package br.com.demo.service;

import br.com.demo.model.Stocks;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

public class StockManagerService {

    public List<Stocks> getStockService(){
        String url = " http://localhost:8080";
        String uri = "/stock";

        List<Stocks> stocks = Arrays.asList(WebClient
                .create(url)
                .get()
                .uri(uri, "")
                .retrieve()
                .bodyToMono(Stocks[].class).block());

        return stocks;
    }
}
