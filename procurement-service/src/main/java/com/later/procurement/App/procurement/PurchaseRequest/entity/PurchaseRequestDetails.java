package com.later.procurement.App.procurement.PurchaseRequest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.procurement.interfaces.commonEntity.DeliveryLocationInterface;
import com.later.procurement.interfaces.commonEntity.ItemInterface;
import com.later.procurement.interfaces.commonEntity.UnitOfMeasureInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "proc_purchase_request_details")
public class PurchaseRequestDetails implements ItemInterface, UnitOfMeasureInterface, DeliveryLocationInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private PurchaseRequest purchaseRequest;
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
    private String descriptionOfGoods;
    @Column(columnDefinition = "text")
    private String descriptionOfGoodsAr;
    private Long unitOfMeasureId;
    private String unitOfMeasureName;
    private String unitOfMeasureCode;
    private String unitOfMeasureNameAr;
    private String unitOfMeasureCodeAr;
    private Double quantity;
    private Double poQuantity;
    private Long deliveryLocationId;
    private String deliveryLocationName;
    private String deliveryLocationNameAr;
    @Column(columnDefinition = "text")
    private String deliveryLocationAddress;
    @Column(columnDefinition = "text")
    private String deliveryLocationAddressAr;
    private String deliveryLocationGoogleMapAddress;
    @Column(columnDefinition = "text")
    private String remarks;
    private Double estUnitPrice;
    private Double estSubTotal;


}
