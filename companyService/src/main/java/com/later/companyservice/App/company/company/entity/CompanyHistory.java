package com.later.companyservice.App.company.company.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class CompanyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nameAr;
    private String hqAddress;
    private String description;
    private String descriptionAr;
    private String logo;
    private String commercialRegister;
    private Integer size;
    private String phone;
    private String website;
    private String VATNumber;
    private Long actionBy;
    private String action;
    private LocalDateTime actionAt = LocalDateTime.now();
}
