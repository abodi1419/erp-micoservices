package com.later.itemservice.App.items.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "item_measure_units", uniqueConstraints = {
        @UniqueConstraint(name = "name_unique", columnNames = {"name", "product"}),
        @UniqueConstraint(name = "name_ar_unique", columnNames = {"name_ar", "product"}),
})
@RequiredArgsConstructor
@Getter
@Setter
public class MeasureUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String code;
    @NotNull
    @NotBlank
    private String codeAr;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String nameAr;
    private String description;
    private String descriptionAr;
    @NotNull
    private Boolean service;
    @OneToMany(mappedBy = "measureUnit")
    @JsonIgnore
    private List<Item> items;

}
