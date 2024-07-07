package com.later.supplierservice.CommonModules.currencies.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Currency {
    private Long id;
    private String name;
    private String nameAr;
    private String shortName;
    private String shortNameAr;
    private Double rate;
    private Boolean active;
}
