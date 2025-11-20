package com.inventory.app.services.impl;

import com.inventory.app.dtos.ItemDto;
import com.inventory.app.dtos.VariantDto;
import com.inventory.app.models.Item;
import com.inventory.app.repository.ItemRepository;
import com.inventory.app.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item create(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item update(Long id, Item item) {
        Item existing = getById(id);
        existing.setName(item.getName());
        existing.setDescription(item.getDescription());
        return itemRepository.save(existing);
    }

    @Override
    public Item getById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item yang anda cari tidak ditemukan."));
    }

    @Override
    public void delete(Long id){
        itemRepository.deleteById(id);
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
