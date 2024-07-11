package com.later.procurement.App.procurement.PurchaseRequest.model;

import com.later.procurement.App.procurement.PurchaseRequest.entity.PurchaseRequestDetails;

import java.io.Serializable;

/**
 * DTO for {@link PurchaseRequestDetails}
 */
public record PurchaseRequestDetailsDto(Long id, Long itemId, String itemName, String itemNameAr, String itemRefCode,
                                        Boolean serviceItem, String itemDescription, String itemDescriptionAr,
                                        String descriptionOfGoods, String descriptionOfGoodsAr, Long unitOfMeasureId,
                                        String unitOfMeasureName, String unitOfMeasureCode, String unitOfMeasureNameAr,
                                        String unitOfMeasureCodeAr, Double quantity, Double poQuantity,
                                        Long deliveryLocationId, String deliveryLocationName,
                                        String deliveryLocationNameAr, String deliveryLocationAddress,
                                        String deliveryLocationAddressAr, String deliveryLocationGoogleMapAddress,
                                        String remarks, Double estUnitPrice,
                                        Double estSubTotal) implements Serializable {
}