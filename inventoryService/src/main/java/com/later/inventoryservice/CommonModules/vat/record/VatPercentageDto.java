package com.later.inventoryservice.CommonModules.vat.record;

import java.io.Serializable;
import java.math.BigDecimal;

public record VatPercentageDto(Long id, BigDecimal percentage, Boolean active,
                               BigDecimal multiplier) implements Serializable {
}