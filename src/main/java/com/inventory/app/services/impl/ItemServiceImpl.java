package com.inventory.app.services.impl;

import com.inventory.app.dtos.ItemDto;
import com.inventory.app.dtos.VariantDto;
import com.inventory.app.models.Item;
import com.inventory.app.models.Price;
import com.inventory.app.models.Stock;
import com.inventory.app.models.Variant;
import com.inventory.app.repository.ItemRepository;
import com.inventory.app.requests.ItemRequest;
import com.inventory.app.services.ItemService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Transactional
    @Override
    public Item create(ItemRequest req) {

        Item item = new Item();
        item.setName(req.getName());
        item.setDescription(req.getDesccription());

        List<Variant> variants = new ArrayList<>();

        if (req.getVariants() != null) {
            for (VariantDto v : req.getVariants()) {

                Variant variant = new Variant();
                variant.setColor(v.getColor());
                variant.setSize(v.getSize());
                variant.setItem(item);

                Price price = new Price();
                price.setPrice(v.getPrice());
                price.setVariant(variant);

                Stock stock = new Stock();
                stock.setQuantity(v.getStock());
                stock.setVariant(variant);

                variant.setPrice(price);
                variant.setStock(stock);

                variants.add(variant);
            }
        }

        item.setVariants(variants);

        return itemRepository.save(item);
    }

    @Override
    public Item update(Long id, ItemRequest req) {
        Item existing = getById(id);
        existing.setName(req.getName());
        existing.setDescription(req.getDesccription());

        List<Variant> oldVariants = existing.getVariants();
        List<Variant> newVariantList = new ArrayList<>();

        if (req.getVariants() != null) {
            for (VariantDto v : req.getVariants()) {

                Variant variant;
                if (v.getId() != null) {
                    variant = oldVariants.stream()
                            .filter(x -> x.getId().equals(v.getId()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Variant tidak ditemukan"));

                } else {
                    variant = new Variant();
                    variant.setItem(existing);
                }

                variant.setColor(v.getColor());
                variant.setSize(v.getSize());

                Price price = variant.getPrice();
                if (price == null) price = new Price();

                price.setPrice(v.getPrice());
                price.setVariant(variant);
                variant.setPrice(price);

                Stock stock = variant.getStock();
                if (stock == null) stock = new Stock();

                stock.setQuantity(v.getStock());
                stock.setVariant(variant);
                variant.setStock(stock);

                newVariantList.add(variant);
            }
        }

        oldVariants.removeIf(old ->
                newVariantList.stream().noneMatch(n ->
                        n.getId() != null && n.getId().equals(old.getId())
                )
        );

        existing.setVariants(newVariantList);

        return itemRepository.save(existing);
    }


    @Override
    public Item getById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item yang anda cari tidak ditemukan."));
    }

    @Override
    public void delete(Long id){
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item tidak ditemukan"));

        itemRepository.delete(item);
    }

    @Override
    public List<ItemDto> getAll(){
        List<Item> items =  itemRepository.findAll();

        return items.stream().map(item -> {
            ItemDto dto = new ItemDto();
            dto.setId(item.getId());
            dto.setName(item.getName());

            List<VariantDto> variantDtos = item.getVariants()
                    .stream()
                    .map(v -> {
                        VariantDto vd = new VariantDto();
                        vd.setId(v.getId());
                        vd.setColor(v.getColor());
                        vd.setSize(v.getSize());
                        vd.setPrice(v.getPrice().getPrice());
                        vd.setStock(v.getStock().getQuantity());
                        return vd;
                    })
                    .collect(Collectors.toList());

            dto.setVariants(variantDtos);

            return dto;
        }).collect(Collectors.toList());
    }

}
