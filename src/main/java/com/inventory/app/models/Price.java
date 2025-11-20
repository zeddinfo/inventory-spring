package com.inventory.app.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal price;

    @OneToOne
    @JoinColumn(name = "variant_id", nullable = false)
    private Variant variant;

    // =========================
    // Constructors
    // =========================
    public Price() {}

    public Price(Long id, BigDecimal price, Variant variant) {
        this.id = id;
        this.price = price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }
}
