package com.inventory.app.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VariantDto {
    private Long id;
    private String color;
    private String size;
    private BigDecimal price;
    private Integer stock;

    public VariantDto() {
    }

    public VariantDto(Long id, String color, String size, BigDecimal price, Integer stock) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.price = price;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
