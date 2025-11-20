package com.inventory.app.services;

import com.inventory.app.dtos.ItemDto;
import com.inventory.app.models.Item;

import java.util.List;

public interface ItemService {
    Item create(Item item);

    Item update(Long id, Item item);

    Item getById(Long id);

    void delete(Long id);

    List<ItemDto> getAll();
}
