package com.later.customerservice.CommonModules.banks.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Bank {
    private Long id;
    private String name;
    private String nameAr;
    private String swift;
    private String description;
    private String descriptionAr;
    private Boolean local;
}
