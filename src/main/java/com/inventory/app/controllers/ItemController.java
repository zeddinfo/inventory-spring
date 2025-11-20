package com.inventory.app.controllers;


import com.inventory.app.exceptions.ResponseUtil;
import com.inventory.app.models.Item;
import com.inventory.app.requests.ItemRequest;
import com.inventory.app.services.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public Map<String, Object> getAll(){
        return ResponseUtil.Ok(itemService.getAll());
    }

    @GetMapping("/{id}")
    public Map<String, Object> getById(@PathVariable Long id) {
        return ResponseUtil.Ok(itemService.getById(id));
    }

    @PostMapping
    public Map<String, Object> create(@RequestBody ItemRequest item) {
        return ResponseUtil.Ok(itemService.create(item));
    }

    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable Long id, @RequestBody ItemRequest item) {
        return ResponseUtil.Ok(itemService.update(id, item));
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseUtil.Ok("Item berhasil dihapus");
    }
}
