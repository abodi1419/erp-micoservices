package com.later.companyservice.CommonModules.addresses.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CityModel {
    private Long id;
    private String nameEn;
    private String nameAr;

    public CityModel(Long id, String nameEn, String nameAr) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameAr = nameAr;
    }

    public CityModel() {
    }
}
