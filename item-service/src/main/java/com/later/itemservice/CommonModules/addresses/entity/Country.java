package com.later.itemservice.CommonModules.addresses.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class Country {
    private Long id;
    private String name;
    private String nameAr;
    private String code;
    private List<City> cities;
}
