package com.inventory.app.repository;

import com.inventory.app.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByVariantId(Long variantId);
}
