package com.inventory.app.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "variant_id", nullable = false)
    private Variant variant;

    // =========================
    // Constructors
    // =========================
    public Stock() {}

    public Stock(Long id, Integer quantity, Variant variant) {
        this.id = id;
        this.quantity = quantity;
        this.variant = variant;
    }


    // =========================
    // Getter & Setter
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }
}
