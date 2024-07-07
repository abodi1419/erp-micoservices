package com.later.customerservice.CommonModules.discipline.entity;

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
