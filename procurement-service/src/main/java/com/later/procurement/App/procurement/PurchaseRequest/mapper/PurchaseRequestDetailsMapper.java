package com.later.procurement.App.procurement.PurchaseRequest.mapper;

import com.later.procurement.App.procurement.PurchaseRequest.entity.PurchaseRequestDetails;
import com.later.procurement.App.procurement.PurchaseRequest.model.PurchaseRequestDetailsDto;

public class PurchaseRequestDetailsMapper {
    public static PurchaseRequestDetailsDto toDto(PurchaseRequestDetails purchaseRequestDetails) {
        if (purchaseRequestDetails == null) {
            return null;
        }
        return new PurchaseRequestDetailsDto(purchaseRequestDetails.getId(), purchaseRequestDetails.getItemId(), purchaseRequestDetails.getItemName(), purchaseRequestDetails.getItemNameAr(), purchaseRequestDetails.getItemRefCode(),
                purchaseRequestDetails.getServiceItem(), purchaseRequestDetails.getItemDescription(), purchaseRequestDetails.getItemDescriptionAr(), purchaseRequestDetails.getDescriptionOfGoods(), purchaseRequestDetails.getDescriptionOfGoodsAr(),
                purchaseRequestDetails.getUnitOfMeasureId(), purchaseRequestDetails.getUnitOfMeasureName(), purchaseRequestDetails.getUnitOfMeasureCode(), purchaseRequestDetails.getUnitOfMeasureNameAr(), purchaseRequestDetails.getUnitOfMeasureCodeAr(),
                purchaseRequestDetails.getQuantity(), purchaseRequestDetails.getPoQuantity(), purchaseRequestDetails.getDeliveryLocationId(), purchaseRequestDetails.getDeliveryLocationName(), purchaseRequestDetails.getDeliveryLocationNameAr(),
                purchaseRequestDetails.getDeliveryLocationAddress(), purchaseRequestDetails.getDeliveryLocationAddressAr(), purchaseRequestDetails.getDeliveryLocationGoogleMapAddress(),
                purchaseRequestDetails.getRemarks(), purchaseRequestDetails.getEstUnitPrice(), purchaseRequestDetails.getEstSubTotal());
    }
}
