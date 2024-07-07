package com.later.companyservice.App.company.company.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class CompanyCreationModel {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String arabicName;
    @NotNull
    @NotBlank
    private String hqAddress;
    private String arabicHqAddress;
    private String description;
    private String descriptionAr;
    private String logo;
    @NotNull
    @NotBlank
    private String commercialRegister;
    @NotNull
    @Positive
    private Integer size;
    @NotNull
    @NotBlank
    private String phone;
    private String website;
    @NotNull
    @NotBlank
    private String vatNumber;
}
