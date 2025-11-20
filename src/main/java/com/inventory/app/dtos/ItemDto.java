package com.inventory.app.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ItemDto {
    private Long id;
    private String name;
    private List<VariantDto> variants;

    public ItemDto() {
    }

    public ItemDto(Long id, String name, List<VariantDto> variants) {
        this.id = id;
        this.name = name;
        this.variants = variants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VariantDto> getVariants() {
        return variants;
    }

    public void setVariants(List<VariantDto> variants) {
        this.variants = variants;
    }
}
