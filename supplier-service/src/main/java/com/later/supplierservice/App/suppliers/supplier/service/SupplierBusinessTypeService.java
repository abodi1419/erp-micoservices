package com.later.supplierservice.App.suppliers.supplier.service;

import com.later.supplierservice.App.suppliers.supplier.entity.Supplier;
import com.later.supplierservice.App.suppliers.supplier.entity.SupplierBusinessType;
import com.later.supplierservice.App.suppliers.supplier.entity.SupplierCertificate;
import com.later.supplierservice.App.suppliers.supplier.model.validation.SupplierBusinessTypeCreationModel;
import com.later.supplierservice.App.suppliers.supplier.repository.SupplierBusinessTypeRepo;
import com.later.supplierservice.App.suppliers.supplier.repository.SupplierCertificateRepo;
import com.later.supplierservice.App.suppliers.supplier.repository.SupplierRepo;
import com.later.supplierservice.CommonModules.businessTypes.entity.BusinessType;
import com.later.supplierservice.CommonModules.businessTypes.service.BusinessTypeService;
import com.later.supplierservice.CommonModules.certificateTypes.entity.CertificateType;
import com.later.supplierservice.CommonModules.models.DocumentModel;
import com.later.supplierservice.CommonModules.services.ValidatorService;
import com.later.supplierservice.Exception.ApiException;
import com.later.supplierservice.constants.ResponseText;
import com.later.supplierservice.constants.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierBusinessTypeService {
    private final SupplierCertificateRepo supplierCertificateRepo;
    private final SupplierRepo supplierRepo;
    private final SupplierBusinessTypeRepo supplierBusinessTypeRepo;
    private final SupplierCertificateService supplierCertificateService;
    private final ValidatorService validator;
    private final BusinessTypeService businessTypeService;


    public String create(SupplierBusinessTypeCreationModel supplierBusinessTypeCreationModel, Long supplierId) throws ApiException {
        Supplier supplier = supplierRepo.findById(supplierId).orElse(null);
        if (supplier == null) {
            throw new ApiException(404, "Supplier not found");
        }
        if (supplier.getSupplierBusinessTypes().stream().anyMatch(t -> t.getBusinessTypeId().equals(supplierBusinessTypeCreationModel.getBusinessType())
                && !t.getDeleted())) {
            throw new ApiException(400, "Business type already exists");
        }
        ;
        SupplierBusinessType supplierBusinessType = validateCreationModel(supplierBusinessTypeCreationModel, null, supplier);
        supplierBusinessTypeRepo.save(supplierBusinessType);
        return ResponseText.CREATED.text();
    }

    public String update(SupplierBusinessTypeCreationModel supplierBusinessTypeCreationModel, Long id) throws ApiException {
        SupplierBusinessType oldSupplierBusinessType = supplierBusinessTypeRepo.findById(id).orElse(null);
        if (oldSupplierBusinessType == null) {
            throw new ApiException(404, "Supplier certificate not found");
        }
        SupplierBusinessType supplierBusinessType = validateCreationModel(supplierBusinessTypeCreationModel,
                oldSupplierBusinessType, oldSupplierBusinessType.getSupplier());
        supplierBusinessTypeRepo.save(supplierBusinessType);
        return ResponseText.UPDATED.text();
    }

    private SupplierBusinessType validateCreationModel(SupplierBusinessTypeCreationModel supplierBusinessTypeCreationModel,
                                                       SupplierBusinessType oldSupplierBusinessType, Supplier supplier) throws ApiException {
        BusinessType businessType = businessTypeService
                .findById(supplierBusinessTypeCreationModel.getBusinessType());

        if (supplier.getSupplierBusinessTypes().stream().anyMatch(t -> (oldSupplierBusinessType != null && !oldSupplierBusinessType.getId().equals(t.getId()))
                && t.getBusinessTypeId().equals(supplierBusinessTypeCreationModel.getBusinessType())
                && !t.getDeleted())) {
            throw new ApiException(400, "Business type already exists");
        }
        SupplierBusinessType supplierBusinessType = new SupplierBusinessType();
        if (oldSupplierBusinessType != null) {
            if (businessType.getRequiredCertificate()) {
                supplierBusinessType.setSupplierCertificate(oldSupplierBusinessType.getSupplierCertificate());
            } else {
                if (oldSupplierBusinessType.getSupplierCertificate() != null) {
                    supplierCertificateService.deleteForBusinessType(oldSupplierBusinessType.getSupplierCertificate());
                }
            }
        }
        if (businessType.getRequiredCertificate()) {
            if (oldSupplierBusinessType == null &&
                    supplierBusinessTypeCreationModel.getSupplierCertificateCreationModel() == null) {
                throw new ApiException(404, "Supplier certificate is required");
            }
            if (oldSupplierBusinessType == null ||
                    supplierBusinessTypeCreationModel.getSupplierCertificateCreationModel().getAttachment() != null) {
                if (oldSupplierBusinessType == null) {
                    validator.validateSpecific(supplierBusinessTypeCreationModel.getSupplierCertificateCreationModel(),
                            List.of("attachment"));
                }


                CertificateType certificateType = businessType.getCertificateType();
                supplierBusinessTypeCreationModel.getSupplierCertificateCreationModel()
                        .setCertificateType(certificateType.getId());
                if (certificateType.getExpires() != null && certificateType.getExpires()) {
                    if (supplierBusinessTypeCreationModel.getSupplierCertificateCreationModel().getExpiryDate() == null) {
                        throw new ApiException(404, "Supplier certificate expiry date is required");
                    }
                }

                if (oldSupplierBusinessType != null) {
                    SupplierCertificate supplierCertificate = supplierCertificateService.updateForBusinessType(
                            supplierBusinessTypeCreationModel.getSupplierCertificateCreationModel(),
                            oldSupplierBusinessType.getSupplierCertificate(), supplier
                    );
                    supplierBusinessType.setSupplierCertificate(supplierCertificate);
                } else {
                    SupplierCertificate supplierCertificate = supplierCertificateService
                            .createForBusinessType(supplierBusinessTypeCreationModel.getSupplierCertificateCreationModel(),
                                    supplier);
                    supplierBusinessType.setSupplierCertificate(supplierCertificate);
                }

            }


        }

        supplierBusinessType.setBusinessTypeId(businessType.getId());
        supplierBusinessType.setBusinessTypeName(businessType.getTypeOfBusiness());
        supplierBusinessType.setBusinessTypeName(businessType.getTypeOfBusinessAr());
        supplierBusinessType.setSupplier(supplier);

        if (oldSupplierBusinessType != null) {
            supplierBusinessType.setId(oldSupplierBusinessType.getId());
        }

        return supplierBusinessType;
    }

    public String delete(Long id) throws ApiException {
        SupplierBusinessType supplierBusinessType = supplierBusinessTypeRepo.findById(id).orElse(null);
        if (supplierBusinessType == null) {
            throw new ApiException(404, "Supplier business type not found");
        }
        if (supplierBusinessType.getSupplier().getStatus().equals(Status.APPROVED.code())
                && supplierBusinessTypeRepo.countBySupplierId(supplierBusinessType.getSupplier().getId()) <= 1) {
            throw new ApiException(404, "Supplier business type is mandatory, at least one type must be present");
        }
        if (supplierBusinessType.getSupplierCertificate() != null) {
            supplierCertificateService.deleteForBusinessType(supplierBusinessType.getSupplierCertificate());
        }
        supplierBusinessTypeRepo.delete(supplierBusinessType);
        return ResponseText.DELETED.text();

    }

    public DocumentModel downloadCertificate(Long id) throws ApiException {
        SupplierBusinessType supplierBusinessType = supplierBusinessTypeRepo.findById(id).orElse(null);
        if (supplierBusinessType == null) {
            throw new ApiException(404, "Supplier business type not found");
        }
        if (supplierBusinessType.getSupplierCertificate() == null) {
            throw new ApiException(400, "No certificate attached with this business type");
        }
        return supplierCertificateService.downloadCertificateForBusinessType(supplierBusinessType.getSupplierCertificate());

    }

}
