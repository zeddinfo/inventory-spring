package com.inventory.app.services;

import com.inventory.app.models.Stock;

public interface StockService {
    Stock setInitialStock(Long variantId, int qty);

    Stock addStock(Long variantId, int qty);

    Stock reduceStock(Long variantId, int qty);

    Stock getByVariant(Long variantId);
}
