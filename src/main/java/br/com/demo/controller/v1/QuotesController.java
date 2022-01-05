package br.com.demo.controller.v1;

import br.com.demo.form.QuotesForm;
import br.com.demo.form.StocksForm;
import br.com.demo.model.Quotes;
import br.com.demo.model.Stocks;
import br.com.demo.repository.QuotesRepository;
import br.com.demo.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Quotes")
public class QuotesController {

    @Autowired
    private QuotesRepository quoteRepository;

    @GetMapping("/list")
    public Page<Quotes> list(@PageableDefault(sort = "StockId", direction = Sort.Direction.DESC, page = 0, size = 10)
                                     Pageable pagination){
        Page<Quotes> quotes = quoteRepository.findAll(pagination);
        return quotes;
    }

    @GetMapping("/quote{id}")
    public ResponseEntity<Quotes> quote(@PathVariable Long id) {
        Optional<Quotes> quote = quoteRepository.findByStockId(id);
        if(quote.isPresent()) {
            return ResponseEntity.ok(quote.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/quote")
    @Transactional
    public ResponseEntity<Quotes> quote(@RequestBody @Valid QuotesForm formularioQuotes, UriComponentsBuilder uriBuilder){

        Quotes quote = formularioQuotes.returnNew();
        quoteRepository.save(quote);

        //Return the status 201
        URI uri = uriBuilder.path("/quote/{stockId}").buildAndExpand(quote.getStockId()).toUri();
        return ResponseEntity.created(uri).body(quote);
   }

}
