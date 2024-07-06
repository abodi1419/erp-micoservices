package com.later.erp.App.company.company.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nameAr;
    private String hqAddress;
    private String hqAddressAr;
    private String description;
    private String descriptionAr;
    private String logo;
    private String commercialRegister;
    private Integer size;
    private String phone;
    private String website;
    private String VATNumber;
}
