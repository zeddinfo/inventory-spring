package com.inventory.app.services.impl;

import com.inventory.app.models.History;
import com.inventory.app.models.Stock;
import com.inventory.app.models.Variant;
import com.inventory.app.repository.HistoryRepository;
import com.inventory.app.repository.VariantRepository;
import com.inventory.app.services.HistoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final VariantRepository variantRepository;
    private final HistoryRepository stockHistoryRepository;

    public HistoryServiceImpl(VariantRepository variantRepository, HistoryRepository stockHistoryRepository) {
        this.variantRepository = variantRepository;
        this.stockHistoryRepository = stockHistoryRepository;
    }

    @Transactional
    @Override
    public List<Variant> processTransaction(Map<Long, Integer> variantQtyMap, String reference) {

        List<Variant> updatedVariants = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : variantQtyMap.entrySet()) {
            Long variantId = entry.getKey();
            Integer qty = entry.getValue();

            Variant variant = variantRepository.findById(variantId)
                    .orElseThrow(() -> new RuntimeException("Variant dengan id " + variantId + " tidak ditemukan"));

            Stock stock = variant.getStock();
            if (stock == null || stock.getQuantity() < qty) {
                throw new RuntimeException("Stok variant " + variantId + " tidak cukup. Tersisa: " +
                        (stock != null ? stock.getQuantity() : 0));
            }

            // Mengurangi Stok
            stock.setQuantity(stock.getQuantity() - qty);
            variant.setStock(stock);

            // Catat History
            History history = new History(variantId, -qty, "OUT", reference);
            stockHistoryRepository.save(history);

            updatedVariants.add(variant);
        }

        return variantRepository.saveAll(updatedVariants);
    }
}
