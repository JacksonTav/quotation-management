package br.com.demo.controller.v1;

import br.com.demo.form.StocksForm;
import br.com.demo.model.Stocks;
import br.com.demo.repository.StockRepository;
import br.com.demo.service.StockManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/Stocks")
public class StocksController {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockManagerService stockManagerService;

    @GetMapping("/list")
    public Page<Stocks> list(@PageableDefault(sort = "StockId", direction = Sort.Direction.DESC, page = 0, size = 10)
                                          Pageable pagination){
        Page<Stocks> stocks = stockRepository.findAll(pagination);
        return stocks;
    }

    @GetMapping("/stock{id}")
    public ResponseEntity<Stocks> stock(@PathVariable Long id) {
        Optional<Stocks> stock = stockRepository.findById(id);
        if(stock.isPresent()) {
            return ResponseEntity.ok(stock.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/stock")
    @Transactional
    public ResponseEntity<Stocks> stock(@RequestBody @Valid StocksForm formularioStock, UriComponentsBuilder uriBuilder){

        //Checking existing
        List<Stocks> listStocks = stockManagerService.getStockService();
        boolean foundStock = containsName(listStocks, formularioStock.getStockId());
        if(foundStock){
            Stocks stock = formularioStock.returnNew();
            stockRepository.save(stock);

            //Return the status 201
            URI uri = uriBuilder.path("/stock/{id}").buildAndExpand(stock.getId()).toUri();
            return ResponseEntity.created(uri).body(stock);
        }

        return ResponseEntity.notFound().build();
    }

    private boolean containsName(final List<Stocks> list, final String stockName){
        return list.stream().filter(o -> o.getId().equals(stockName)).findFirst().isPresent();
    }
}
