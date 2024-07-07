package com.later.customerservice.CommonModules.vat.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
@Setter
public class VatPercentage {
    private Long id;
    private BigDecimal percentage;
    private Boolean active;
    private BigDecimal multiplier;
}
