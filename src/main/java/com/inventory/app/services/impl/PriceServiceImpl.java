package com.inventory.app.services.impl;

import com.inventory.app.models.Price;
import com.inventory.app.models.Variant;
import com.inventory.app.repository.PriceRepository;
import com.inventory.app.repository.VariantRepository;
import com.inventory.app.services.PriceService;

public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final VariantRepository variantRepository;

    public PriceServiceImpl(PriceRepository priceRepository, VariantRepository variantRepository) {
        this.priceRepository = priceRepository;
        this.variantRepository = variantRepository;
    }

    @Override
    public Price setPrice(Long variantId, Price price) {
        Variant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        price.setVariant(variant);

        return priceRepository.save(price);
    }

    @Override
    public Price getByVariant(Long variantId) {
        Variant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        return variant.getPrice();
    }
}
