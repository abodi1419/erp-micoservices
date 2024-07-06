package com.later.customerservice.CommonModules.addresses.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class City {
    private Long id;
    private String nameAr;
    private String nameEn;
    private Country country;
}
