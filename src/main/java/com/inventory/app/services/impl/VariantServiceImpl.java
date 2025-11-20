package com.inventory.app.services.impl;

import com.inventory.app.models.Item;
import com.inventory.app.models.Variant;
import com.inventory.app.repository.ItemRepository;
import com.inventory.app.repository.VariantRepository;
import com.inventory.app.services.VariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VariantServiceImpl implements VariantService {
    private final VariantRepository variantRepository;
    private final ItemRepository itemRepository;

    public VariantServiceImpl(VariantRepository variantRepository, ItemRepository itemRepository) {
        this.variantRepository = variantRepository;
        this.itemRepository = itemRepository;
    }

        @Override
        public Variant create(Long itemId, Variant variant) {
            Item item = itemRepository.findById(itemId)
                    .orElseThrow(() -> new RuntimeException("Mapping item yang anda pilih tidak ditemukan."));

            variant.setItem(item);

            return variantRepository.save(variant);
        }

    @Override
    public Variant update(Long id, Variant variant) {
        Variant existing = getById(id);

        existing.setSize(variant.getSize());
        existing.setColor(variant.getColor());

        return variantRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        variantRepository.deleteById(id);
    }

    @Override
    public Variant getById(Long id) {
        return variantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variant yang anda pilih tidak ditemukan"));
    }

    @Override
    public List<Variant> getByItem(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Mapping item yang anda pilih tidak ditemukan."))
                .getVariants();
    }
}
