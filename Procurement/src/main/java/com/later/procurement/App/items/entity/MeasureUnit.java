package com.later.procurement.App.items.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class MeasureUnit {
    private Long id;
    private String code;
    private String codeAr;
    private String name;
    private String nameAr;
    private String description;
    private String descriptionAr;
    private Boolean service;
    private List<Item> items;

}
