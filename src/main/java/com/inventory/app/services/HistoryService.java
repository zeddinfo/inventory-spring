package com.inventory.app.services;

import com.inventory.app.models.Variant;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;

public interface HistoryService {
    @Transactional
    List<Variant> processTransaction(Map<Long, Integer> variantQtyMap, String reference);
}
