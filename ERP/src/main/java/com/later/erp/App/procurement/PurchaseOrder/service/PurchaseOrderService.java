package com.later.erp.App.procurement.PurchaseOrder.service;

import com.later.erp.Authorization.Security.Auth.entities.Employee;
import com.later.erp.App.procurement.ApprovalService.service.ProcurementApprovalService;
import com.later.erp.App.procurement.DocumentService.entity.ProcurementDocument;
import com.later.erp.App.procurement.DocumentService.service.ProcurementDocumentService;
import com.later.erp.App.procurement.PurchaseOrder.entity.PurchaseOrder;
import com.later.erp.App.procurement.PurchaseOrder.entity.PurchaseOrderDetails;
import com.later.erp.App.procurement.PurchaseOrder.model.PurchaseOrderCreationModel;
import com.later.erp.App.procurement.PurchaseOrder.model.PurchaseOrderDetailsCreationModel;
import com.later.erp.App.procurement.PurchaseOrder.repository.PurchaseOrderDetailsRepo;
import com.later.erp.App.procurement.PurchaseOrder.repository.PurchaseOrderRepo;
import com.later.erp.App.procurement.PurchaseRequest.entity.PurchaseRequest;
import com.later.erp.App.procurement.PurchaseRequest.entity.PurchaseRequestDetails;
import com.later.erp.App.procurement.PurchaseRequest.model.PurchaseRequestShortModel;
import com.later.erp.App.procurement.PurchaseRequest.service.PurchaseRequestService;
import com.later.erp.App.suppliers.cashSupplier.entity.CashSupplier;
import com.later.erp.App.suppliers.cashSupplier.entity.CashSupplierContact;
import com.later.erp.App.suppliers.cashSupplier.model.CashSupplierListModel;
import com.later.erp.App.suppliers.cashSupplier.service.CashSupplierService;
import com.later.erp.App.suppliers.supplier.entity.Supplier;
import com.later.erp.App.suppliers.supplier.entity.SupplierContact;
import com.later.erp.App.suppliers.supplier.model.SupplierListModel;
import com.later.erp.App.suppliers.supplier.service.SupplierContactService;
import com.later.erp.App.suppliers.supplier.service.SupplierService;
import com.later.erp.CommonModules.company.costCenter.entity.CostCenter;
import com.later.erp.CommonModules.company.costCenter.service.CostCenterService;
import com.later.erp.CommonModules.currencies.entity.Currency;
import com.later.erp.CommonModules.currencies.service.CurrencyService;
import com.later.erp.CommonModules.deliveryLocation.entity.DeliveryLocation;
import com.later.erp.CommonModules.deliveryLocation.service.DeliveryLocationService;
import com.later.erp.CommonModules.vat.record.VatPercentageDto;
import com.later.erp.CommonModules.vat.service.VatPercentageService;
import com.later.erp.Exception.ApiException;
import com.later.erp.constants.ResponseText;
import com.later.erp.constants.Status;
import com.later.erp.constants.SystemCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {
    private final PurchaseOrderRepo purchaseOrderRepo;
    private final PurchaseOrderDetailsRepo purchaseOrderDetailsRepo;

    private final ProcurementApprovalService procurementApprovalService;
    private final PurchaseRequestService purchaseRequestService;
    private final VatPercentageService vatPercentageService;
    private final DeliveryLocationService deliveryLocationService;
    private final ProcurementDocumentService procurementDocumentService;
    private final CurrencyService currencyService;
    private final SupplierService supplierService;
    private final SupplierContactService supplierContactService;
    private final CashSupplierService cashSupplierService;
    private final CostCenterService costCenterService;

    public List<PurchaseOrder> findAll(Employee loginUser) {
        return purchaseOrderRepo.findAll();
    }

    public PurchaseOrder findById(Long id, Employee loginUser) throws ApiException {
        PurchaseOrder purchaseOrder = purchaseOrderRepo.findById(id).orElse(null);
        if (purchaseOrder == null || (!purchaseOrder.getCreatedById().equals(loginUser.getId())
                && purchaseOrder.getApproval().stream().noneMatch(a -> a.getApproverId().equals(loginUser.getId())))) {
            throw new ApiException(404, "Purchase order not found");
        }
        return purchaseOrder;
    }

    public List<PurchaseRequestShortModel> listPurchaseRequests(Long costCenterId, Employee loginUser) throws ApiException {
        return purchaseRequestService.listForBuyer(costCenterId, loginUser);
    }

    public PurchaseRequest findPurchaseRequestById(Long purchaseRequestId, Employee loginUser) throws ApiException {
        return purchaseRequestService.findByIdForPurchaseOrder(purchaseRequestId, loginUser);
    }

    public List<CostCenter> listCostCenters() {
        return costCenterService.findAll();
    }

    public List<SupplierListModel> listSuppliers() {
        return supplierService.findAllForOtherSystems();
    }

    public List<SupplierContact> listSupplierContacts(Long supplierId) {
        return supplierContactService.findAllBySupplierIdForOtherSystems(supplierId);
    }

    private List<CashSupplierListModel> listCashSuppliers() {
        return cashSupplierService.findAllForOtherSystems();
    }

    public String create(PurchaseOrderCreationModel purchaseOrderCreationModel, Employee loginUser) throws ApiException {
        PurchaseOrder purchaseOrder = validateCreationModel(purchaseOrderCreationModel, loginUser);
        List<ProcurementDocument> procurementDocuments = null;
        if (purchaseOrderCreationModel.getAttachments() != null && !purchaseOrderCreationModel.getAttachments().isEmpty()) {
            procurementDocuments = procurementDocumentService.uploadFromTemp(purchaseOrderCreationModel.getAttachments(),
                    SystemCode.PURCHASE_ORDER.systemCode());
        }
        if (purchaseOrder.getRevision() && !purchaseOrder.getPurchaseRequest().getId()
                .equals(purchaseOrder.getRevisedPurchaseOrder().getPurchaseRequest().getId())) {
            purchaseRequestService.saveDetailsForPurchaseOrder(
                    purchaseOrder.getRevisedPurchaseOrder().getPurchaseRequest().getPurchaseRequestDetails());

        }
        purchaseRequestService.saveDetailsForPurchaseOrder(purchaseOrder.getPurchaseRequest().getPurchaseRequestDetails());
        purchaseOrder = purchaseOrderRepo.save(purchaseOrder);
        purchaseOrderDetailsRepo.saveAll(purchaseOrder.getPurchaseOrderDetails());
        if (procurementDocuments != null) {
            procurementDocumentService.saveDocument(procurementDocuments, purchaseOrder, false, SystemCode.PURCHASE_ORDER.systemCode());
        }
        return ResponseText.CREATED.text();
    }

    private PurchaseOrder validateCreationModel(PurchaseOrderCreationModel purchaseOrderCreationModel, Employee loginUser) throws ApiException {
// DATA GATHERING
        PurchaseRequest purchaseRequest = purchaseRequestService.findByIdForPurchaseOrder(purchaseOrderCreationModel.getPurchaseRequestId(), loginUser);
        List<VatPercentageDto> vatPercentages = vatPercentageService.findAll();
        VatPercentageDto shippingVatPercentage = vatPercentages.stream()
                .filter(v -> v.id().equals(purchaseOrderCreationModel.getShippingVatPercentage()))
                .findFirst().orElse(null);
        if (shippingVatPercentage == null || !shippingVatPercentage.active()) {
            throw new ApiException(404, "Vat percentage not found");
        }
        Currency currency = currencyService.findById(purchaseOrderCreationModel.getCurrency());
        Supplier supplier = null;
        SupplierContact supplierContact = null;
        CashSupplier cashSupplier = null;
        CashSupplierContact cashSupplierContact = null;
        if (!purchaseOrderCreationModel.getPettyCash()) {
            supplier = supplierService.findByIdForOtherSystems(purchaseOrderCreationModel.getSupplierId());
            supplierContact = supplierContactService.findByIdForOtherSystems(purchaseOrderCreationModel.getSupplierContact(), supplier);
        } else {
            cashSupplier = cashSupplierService.findByIdForOtherSystems(purchaseOrderCreationModel.getSupplierId());
            cashSupplierContact = cashSupplierService.findContactByIdForOtherSystems(purchaseOrderCreationModel.getSupplierContact(), cashSupplier);
        }
        if (!PurchaseOrderConstants.Type.checkById(purchaseOrderCreationModel.getType())) {
            throw new ApiException(400, "Type not found");
        }
        DeliveryLocation deliveryLocation = deliveryLocationService
                .findById(purchaseOrderCreationModel.getDeliveryLocation());
        PurchaseOrder revised = null;
        if (purchaseOrderCreationModel.getRevision()) {
            if (purchaseOrderCreationModel.getRevisedPurchaseOrder() == null) {
                throw new ApiException(400, "Revised purchase order not found");
            }
            revised = purchaseOrderRepo.findById(purchaseOrderCreationModel.getRevisedPurchaseOrder()).orElse(null);
            if (revised == null || !revised.getStatus().equals(Status.APPROVED.code())) {
                throw new ApiException(404, "Revised purchase order not found");
            }
            if (revised.getPurchaseRequest().getId().equals(purchaseRequest.getId())) {
                for (PurchaseOrderDetails purchaseOrderDetails : revised.getPurchaseOrderDetails()) {
                    for (PurchaseRequestDetails purchaseRequestDetails : purchaseRequest.getPurchaseRequestDetails()) {
                        if (purchaseOrderDetails.getPurchaseRequestDetails().getId().equals(purchaseOrderDetails.getId())) {
                            purchaseRequestDetails.setPoQuantity(purchaseRequestDetails.getPoQuantity() - purchaseOrderDetails.getQuantity());
                        }
                    }
                }
            }
        }
//////////////////////////////////////////////////////////////////////////////////////
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setPurchaseRequest(purchaseRequest);
        if (!purchaseOrderCreationModel.getPettyCash()) {
            purchaseOrder.setSupplierId(supplier.getId());
            purchaseOrder.setSupplierContactId(supplierContact.getId());
            purchaseOrder.setSupplierName(supplier.getCompanyName());
            purchaseOrder.setSupplierNameAr(supplier.getCompanyNameAr());
            purchaseOrder.setSupplierCommercialRegister(supplier.getCommercialRegister());
            purchaseOrder.setSupplierVatNumber(supplier.getVatNumber());
            purchaseOrder.setLocalSupplier(supplier.getLocal());
            purchaseOrder.setSupplierContactId(supplierContact.getId());
            purchaseOrder.setSupplierContactName(supplierContact.getContactName());
            purchaseOrder.setSupplierContactNameAr(supplierContact.getContactNameAr());
            purchaseOrder.setSupplierContactEmail(supplierContact.getContactEmail());
            purchaseOrder.setSupplierContactPhone(supplierContact.getContactPhone());
            purchaseOrder.setSupplierContactJobTitle(supplierContact.getContactJobTitle());
            purchaseOrder.setSupplierContactJobTitleAr(supplierContact.getContactJobTitleAr());
        } else {
            purchaseOrder.setSupplierId(cashSupplier.getId());
            purchaseOrder.setSupplierName(cashSupplier.getCompanyName());
            purchaseOrder.setSupplierNameAr(cashSupplier.getCompanyNameAr());
            purchaseOrder.setSupplierCommercialRegister(cashSupplier.getCommercialRegister());
            purchaseOrder.setSupplierVatNumber(cashSupplier.getVatNumber());
            purchaseOrder.setSupplierContactId(cashSupplierContact.getId());
            purchaseOrder.setSupplierContactName(cashSupplierContact.getContactName());
            purchaseOrder.setSupplierContactNameAr(cashSupplierContact.getContactNameAr());
            purchaseOrder.setSupplierContactEmail(cashSupplierContact.getContactEmail());
            purchaseOrder.setSupplierContactPhone(cashSupplierContact.getContactPhone());
            purchaseOrder.setSupplierContactJobTitle(cashSupplierContact.getContactJobTitle());
            purchaseOrder.setSupplierContactJobTitleAr(cashSupplierContact.getContactJobTitleAr());
        }
        purchaseOrder.setCurrencyId(currency.getId());
        purchaseOrder.setCurrencyName(currency.getName());
        purchaseOrder.setCurrencyNameAr(currency.getNameAr());
        purchaseOrder.setCurrencyShortName(currency.getShortName());
        purchaseOrder.setCurrencyShortNameAr(currency.getShortNameAr());
        purchaseOrder.setPettyCash(purchaseOrderCreationModel.getPettyCash());
        purchaseOrder.setType(purchaseOrderCreationModel.getType());
        purchaseOrder.setCostCenterId(purchaseRequest.getCostCenterId());
        purchaseOrder.setCostCenterName(purchaseRequest.getCostCenterName());
        purchaseOrder.setCostCenterNameAr(purchaseRequest.getCostCenterNameAr());
        purchaseOrder.setCostCenterRefCode(purchaseRequest.getCostCenterRefCode());
        purchaseOrder.setDepartmentId(purchaseRequest.getDepartmentId());
        purchaseOrder.setDepartmentName(purchaseRequest.getDepartmentName());
        purchaseOrder.setDepartmentNameAr(purchaseRequest.getDepartmentNameAr());
        purchaseOrder.setDepartmentRefCode(purchaseRequest.getDepartmentRefCode());
        purchaseOrder.setDivisionId(purchaseRequest.getDivisionId());
        purchaseOrder.setDivisionName(purchaseRequest.getDivisionName());
        purchaseOrder.setDivisionNameAr(purchaseRequest.getDivisionNameAr());
        purchaseOrder.setDeliveryLocation(deliveryLocation);
        purchaseOrder.setDeliveryTerms(purchaseOrderCreationModel.getDeliveryTerms());
        purchaseOrder.setPaymentTerms(purchaseOrderCreationModel.getPaymentTerms());
        purchaseOrder.setWarrantyTerms(purchaseOrderCreationModel.getWarrantyTerms());
        purchaseOrder.setRevisedPurchaseOrder(revised);
        purchaseOrder.setCreatedById(loginUser.getId());
        purchaseOrder.setCreatedByName(loginUser.getEmployeeFullName());
        purchaseOrder.setCreatedByNameAr(loginUser.getEmployeeFullNameAr());
        purchaseOrder.setCreatedByCompanyNumber(loginUser.getEmployeeCompanyNumber());
        purchaseOrder.setAdvancePayment(BigDecimal.valueOf(purchaseOrderCreationModel.getAdvancePayment()));
        purchaseOrder.setShippingCost(BigDecimal.valueOf(purchaseOrderCreationModel.getShippingCost()));
        purchaseOrder.setRevision(purchaseOrderCreationModel.getRevision());
        purchaseOrder.setShippingVatPercentage(shippingVatPercentage.percentage());
        purchaseOrder.setShippingVatMultiplier(shippingVatPercentage.multiplier());
        purchaseOrder.setShippingTotal(purchaseOrder.getShippingCost().multiply(shippingVatPercentage.multiplier())
                .setScale(2, RoundingMode.HALF_UP));
        purchaseOrder.setShippingVatAmount(purchaseOrder.getShippingCost()
                .multiply(shippingVatPercentage.multiplier().subtract(BigDecimal.ONE)));
        BigDecimal totalItems = BigDecimal.ZERO;
        BigDecimal totalItemVatAmount = BigDecimal.ZERO;
        List<PurchaseOrderDetails> purchaseOrderDetailsList = new ArrayList<>();
        for (PurchaseOrderDetailsCreationModel purchaseOrderDetailsCreationModel :
                purchaseOrderCreationModel.getPurchaseOrderDetailsCreationModels()) {

            for (PurchaseRequestDetails purchaseRequestDetails : purchaseRequest.getPurchaseRequestDetails()) {
                if (purchaseOrderDetailsCreationModel.getPurchaseRequestDetails().equals(purchaseRequestDetails.getId())) {
                    Double prRemaining = purchaseRequestDetails.getQuantity() - purchaseRequestDetails.getPoQuantity();
                    if (prRemaining < purchaseOrderDetailsCreationModel.getQuantity()) {
                        throw new ApiException(400, "Insufficient quantity for item: " + purchaseRequestDetails.getItemName());
                    }
                    VatPercentageDto vatPercentage = vatPercentages.stream()
                            .filter(v -> v.id().equals(purchaseOrderDetailsCreationModel.getVatPercentage()))
                            .findFirst().orElse(null);
                    if (vatPercentage == null) {
                        throw new ApiException(404, "Vat percentage not found for item: " + purchaseRequestDetails.getItemName());
                    }
                    purchaseRequestDetails.setPoQuantity(purchaseRequestDetails.getPoQuantity() + purchaseOrderDetailsCreationModel.getQuantity());
                    PurchaseOrderDetails purchaseOrderDetails = new PurchaseOrderDetails();
                    purchaseOrderDetails.setPurchaseOrder(purchaseOrder);
                    purchaseOrderDetails.setPurchaseRequestDetails(purchaseRequestDetails);
                    purchaseOrderDetails.setItemId(purchaseRequestDetails.getItemId());
                    purchaseOrderDetails.setItemName(purchaseRequestDetails.getItemName());
                    purchaseOrderDetails.setItemNameAr(purchaseRequestDetails.getItemNameAr());
                    purchaseOrderDetails.setItemDescription(purchaseRequestDetails.getItemDescription());
                    purchaseOrderDetails.setItemDescriptionAr(purchaseRequestDetails.getItemDescriptionAr());
                    purchaseOrderDetails.setItemRefCode(purchaseRequestDetails.getItemRefCode());
                    purchaseOrderDetails.setServiceItem(purchaseRequestDetails.getServiceItem());
                    purchaseOrderDetails.setUnitOfMeasureId(purchaseRequestDetails.getUnitOfMeasureId());
                    purchaseOrderDetails.setUnitOfMeasureCode(purchaseRequestDetails.getUnitOfMeasureCode());
                    purchaseOrderDetails.setUnitOfMeasureCodeAr(purchaseRequestDetails.getUnitOfMeasureCodeAr());
                    purchaseOrderDetails.setUnitOfMeasureName(purchaseRequestDetails.getUnitOfMeasureName());
                    purchaseOrderDetails.setUnitOfMeasureNameAr(purchaseRequestDetails.getUnitOfMeasureNameAr());
                    purchaseOrderDetails.setDeliveryLocationId(purchaseRequestDetails.getDeliveryLocationId());
                    purchaseOrderDetails.setDeliveryLocationAddress(purchaseRequestDetails.getDeliveryLocationAddress());
                    purchaseOrderDetails.setDeliveryLocationAddressAr(purchaseRequestDetails.getDeliveryLocationAddressAr());
                    purchaseOrderDetails.setDeliveryLocationName(purchaseRequestDetails.getDeliveryLocationName());
                    purchaseOrderDetails.setDeliveryLocationNameAr(purchaseRequestDetails.getDeliveryLocationNameAr());
                    purchaseOrderDetails.setQuantity(purchaseOrderDetailsCreationModel.getQuantity());
                    purchaseOrderDetails.setRemarks(purchaseOrderDetailsCreationModel.getRemarks());
                    purchaseOrderDetails.setUnitPrice(BigDecimal.valueOf(purchaseOrderDetailsCreationModel.getUnitPrice()));
                    purchaseOrderDetails.setUnitDiscount(BigDecimal.valueOf(purchaseOrderDetailsCreationModel.getUnitDiscount()));
                    purchaseOrderDetails.setSubTotal(calculateItemSubTotal(purchaseOrderDetails));
                    purchaseOrderDetails.setSubTotalVatAmount(purchaseOrderDetails.getSubTotal()
                            .multiply(vatPercentage.multiplier().subtract(BigDecimal.ONE))
                            .setScale(2, RoundingMode.HALF_UP));
                    totalItems = totalItems.add(purchaseOrderDetails.getSubTotal()).setScale(2, RoundingMode.HALF_UP);
                    totalItemVatAmount = totalItemVatAmount.add(purchaseOrderDetails.getSubTotalVatAmount())
                            .setScale(2, RoundingMode.HALF_UP);
                    purchaseOrderDetails.setVatPercentage(vatPercentage.percentage());
                    purchaseOrderDetails.setVatMultiplier(vatPercentage.multiplier());
                    purchaseOrderDetailsList.add(purchaseOrderDetails);
                }
            }
        }
        purchaseOrder.setPurchaseOrderDetails(purchaseOrderDetailsList);
        purchaseOrder.setItemsTotal(totalItems);
        purchaseOrder.setItemsTotalVatAmount(totalItemVatAmount);
        purchaseOrder.setGrandTotal(purchaseOrder.getShippingTotal().add(purchaseOrder.getItemsTotal())
                .add(purchaseOrder.getItemsTotalVatAmount()));

        return purchaseOrder;
    }

    private BigDecimal calculateItemSubTotal(PurchaseOrderDetails purchaseOrderDetails) {
        return purchaseOrderDetails.getUnitPrice()
                .subtract(purchaseOrderDetails.getUnitDiscount())
                .multiply(BigDecimal.valueOf(purchaseOrderDetails.getQuantity()));
    }


}
