package br.com.demo.repository;

import br.com.demo.model.Quotes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuotesRepository extends JpaRepository<Quotes, Long> {
    Optional<Quotes> findByStockId(Long stockId);
}
