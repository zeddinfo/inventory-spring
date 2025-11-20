package com.inventory.app.services;

import com.inventory.app.models.Variant;

import java.util.List;

public interface VariantService {
    Variant create(Long itemId, Variant variant);

    Variant update(Long id, Variant variant);

    void delete(Long id);

    Variant getById(Long id);

    List<Variant> getByItem(Long itemId);
}
