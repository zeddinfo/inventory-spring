package com.inventory.app.services;

import com.inventory.app.models.Price;

public interface PriceService {
    Price setPrice(Long variantId, Price price);

    Price getByVariant(Long variantId);
}
