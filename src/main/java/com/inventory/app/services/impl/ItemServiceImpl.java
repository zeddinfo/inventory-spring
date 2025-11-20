package com.inventory.app.services.impl;

import com.inventory.app.models.Item;
import com.inventory.app.repository.ItemRepository;
import com.inventory.app.services.ItemService;

import java.util.List;

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
    public List<Item> getAll(){
        return itemRepository.findAll();
    }

}
