package com.later.erp.App.procurement.PurchaseRequest.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class PurchaseRequestShortModel {
    private Long id;
    private String costCenter;
    private String costCenterAr;
    private String division;
    private String divisionAr;
    private String discipline;
    private String disciplineAr;
    private String refCode;
    private String createdBy;
    private String createdByAr;
    private String createdAt;
    private String deliveryDate;
    private Double estTotal;

    public PurchaseRequestShortModel(Long id, String costCenter, String costCenterAr,
                                     String division, String discipline, String disciplineAr,
                                     String refCode, String createdBy, String createdByAr, LocalDateTime createdAt, LocalDate deliveryDate, Double estTotal) {
        this.id = id;
        this.costCenter = costCenter;
        this.division = division;
        this.discipline = discipline;
        this.refCode = refCode;
        this.createdBy = createdBy;
        this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.deliveryDate = deliveryDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.estTotal = estTotal;
        this.costCenterAr = costCenterAr;
        this.disciplineAr = disciplineAr;
        this.createdByAr = createdByAr;

    }
}
