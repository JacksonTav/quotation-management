package br.com.demo.repository;

import br.com.demo.model.Stocks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stocks, Long> {
    Page<Stocks> findAll(Pageable pageable);
}
