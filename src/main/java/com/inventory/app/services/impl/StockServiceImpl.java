package com.inventory.app.services.impl;

import com.inventory.app.models.Stock;
import com.inventory.app.models.Variant;
import com.inventory.app.repository.StockRepository;
import com.inventory.app.repository.VariantRepository;
import com.inventory.app.services.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final VariantRepository variantRepository;

    public StockServiceImpl(StockRepository stockRepository, VariantRepository variantRepository) {
        this.stockRepository = stockRepository;
        this.variantRepository = variantRepository;
    }

    @Override
    public Stock setInitialStock(Long variantId, int qty) {
        Variant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Mapping varian yang anda pilih tidak ditemukan"));

        Stock stock = new Stock();
        stock.setVariant(variant);
        stock.setQuantity(qty);

        return stockRepository.save(stock);
    }

    @Override
    public Stock addStock(Long variantId, int qty) {
        Stock stock = getByVariant(variantId);
        stock.setQuantity(stock.getQuantity() + qty);
        return stockRepository.save(stock);
    }

    @Override
    public Stock reduceStock(Long variantId, int qty) {
        Stock stock = getByVariant(variantId);

        if (stock.getQuantity() < qty) {
            throw new RuntimeException("Stok tidak mencukupi");
        }

        stock.setQuantity(stock.getQuantity() - qty);
        return stockRepository.save(stock);
    }

    @Override
    public Stock getByVariant(Long variantId) {
        return stockRepository.findByVariantId(variantId)
                .orElseThrow(() -> new RuntimeException("Stock yang adna cari tidak ditemukan"));
    }
}
