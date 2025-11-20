package com.inventory.app.services;

import com.inventory.app.dtos.ItemDto;
import com.inventory.app.models.Item;
import com.inventory.app.requests.ItemRequest;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ItemService {

    @Transactional
    Item create(ItemRequest item);

    Item update(Long id, Item item);

    Item getById(Long id);

    void delete(Long id);

    List<ItemDto> getAll();
}
