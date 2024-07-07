package com.later.supplierservice.CommonModules.industryTypes.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@Getter
@Setter
public class IndustryType {
    private Long id;
    private String industry;
    private String industryAr;
}
