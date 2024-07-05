package com.later.itemservice.CommonModules.vat.entity;

import jakarta.persistence.*;
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
