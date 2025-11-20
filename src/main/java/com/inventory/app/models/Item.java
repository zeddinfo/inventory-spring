package com.inventory.app.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id ;

    @Column(nullable = false, unique = false)
    public String Name;

    @Column(nullable = true)
    public  String Description;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Variant> variants;

    public Item() {
    }

    public Item(Long id, String name, String description, List<Variant> variants) {
        this.Id = id;
        this.Name = name;
        this.Description= description;
        this.variants = variants;
    }

    // =========================
    // Getters and Setters
    // =========================

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }
}
