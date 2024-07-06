package com.later.erp.App.items.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ItemCategoryCreationModel {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String nameAr;
    private String description;
    private String descriptionAr;
    private Long parentId;


}
