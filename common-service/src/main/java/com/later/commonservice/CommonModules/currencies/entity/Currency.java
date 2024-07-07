package com.later.commonservice.CommonModules.currencies.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "common_currencies")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nameAr;
    private String shortName;
    private String shortNameAr;
    @Column(columnDefinition = "decimal(6, 3) unsigned")
    private Double rate;
    private Boolean active;
}
