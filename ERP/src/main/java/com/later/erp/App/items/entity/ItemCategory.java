package com.later.erp.App.items.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "item_item_categories", uniqueConstraints = {
        @UniqueConstraint(name = "unique_name", columnNames = {"name"}),
        @UniqueConstraint(name = "unique_name_ar", columnNames = {"nameAr"})
})
@RequiredArgsConstructor
@Getter
@Setter
public class ItemCategory {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String nameAr;
    private String description;
    private String descriptionAr;
    @ManyToOne
    private ItemCategory parent;
    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<ItemCategory> itemCategories;
    @OneToMany(mappedBy = "itemCategory")
    @JsonIgnore
    private List<Item> items;

}
