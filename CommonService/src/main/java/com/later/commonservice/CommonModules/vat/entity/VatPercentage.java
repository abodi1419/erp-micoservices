package com.later.commonservice.CommonModules.vat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "common_vat_percentages")
public class VatPercentage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal percentage;
    @Column(columnDefinition = "bit default 1")
    private Boolean active;
    @Column(nullable = false)
    private BigDecimal multiplier;
}
