package com.later.companyservice.CommonModules.discipline.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Discipline {
    private Long id;
    private String code;
    private String name;
    private String nameAr;
    private String description;
    private String descriptionAr;
    private Boolean supportAllItems;
}