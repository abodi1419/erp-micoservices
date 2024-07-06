package com.later.erp.App.procurement.PurchaseOrder.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.erp.App.procurement.PurchaseRequest.entity.PurchaseRequestDetails;
import com.later.erp.interfaces.commonEntity.DeliveryLocationInterface;
import com.later.erp.interfaces.commonEntity.ItemInterface;
import com.later.erp.interfaces.commonEntity.UnitOfMeasureInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "proc_purchase_order_details")
public class PurchaseOrderDetails implements ItemInterface, UnitOfMeasureInterface, DeliveryLocationInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private PurchaseOrder purchaseOrder;
    private Long itemId;
    private String itemName;
    private String itemNameAr;
    private String itemRefCode;
    private Boolean serviceItem;
    @Column(columnDefinition = "text")
    private String itemDescription;
    @Column(columnDefinition = "text")
    private String itemDescriptionAr;
    @Column(columnDefinition = "text")
    private String remarks;
    private Long unitOfMeasureId;
    private String unitOfMeasureName;
    private String unitOfMeasureCode;
    private String unitOfMeasureCodeAr;
    private String unitOfMeasureNameAr;
    private Double quantity;
    private Long deliveryLocationId;
    private String deliveryLocationName;
    private String deliveryLocationNameAr;
    @Column(columnDefinition = "text")
    private String deliveryLocationAddress;
    @Column(columnDefinition = "text")
    private String deliveryLocationAddressAr;
    private String deliveryLocationGoogleMapAddress;
    private BigDecimal vatPercentage;
    private BigDecimal vatMultiplier;
    private BigDecimal subTotalVatAmount;
    private BigDecimal unitPrice;
    private BigDecimal unitDiscount;
    private BigDecimal subTotal;
    @ManyToOne
    private PurchaseRequestDetails purchaseRequestDetails;
}
