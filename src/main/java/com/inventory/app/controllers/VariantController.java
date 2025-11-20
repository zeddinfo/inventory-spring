package com.inventory.app.controllers;

import com.inventory.app.exceptions.ResponseUtil;
import com.inventory.app.models.Variant;
import com.inventory.app.services.VariantService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public class VariantController {
    private final VariantService variantService;

    public VariantController(VariantService variantService) {
        this.variantService = variantService;
    }


    @GetMapping("/{id}")
    public Map<String, Object> getById(@PathVariable Long id) {
        return ResponseUtil.Ok(variantService.getById(id));
    }

    @PostMapping("/item/{itemId}")
    public Map<String, Object> create(
            @PathVariable Long itemId,
            @RequestBody Variant variant
    ) {
        return ResponseUtil.Ok(variantService.create(itemId, variant));
    }

    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable Long id, @RequestBody Variant variant) {
        return ResponseUtil.Ok(variantService.update(id, variant));
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        variantService.delete(id);
        return ResponseUtil.Ok("Variant deleted");
    }
}
