package com.later.erp.App.suppliers.supplier.service;

import com.later.erp.App.suppliers.DocumentService.entity.SupplierDocument;
import com.later.erp.App.suppliers.DocumentService.service.SupplierDocumentService;
import com.later.erp.App.suppliers.supplier.entity.Supplier;
import com.later.erp.App.suppliers.supplier.entity.SupplierCertificate;
import com.later.erp.App.suppliers.supplier.model.validation.SupplierCertificateCreationModel;
import com.later.erp.App.suppliers.supplier.repository.SupplierCertificateRepo;
import com.later.erp.App.suppliers.supplier.repository.SupplierRepo;
import com.later.erp.CommonModules.certificateTypes.entity.CertificateType;
import com.later.erp.CommonModules.certificateTypes.service.CertificateTypeService;
import com.later.erp.CommonModules.models.DocumentModel;
import com.later.erp.CommonServices.DocumentService.entity.Document;
import com.later.erp.Exception.ApiException;
import com.later.erp.constants.ResponseText;
import com.later.erp.constants.Status;
import com.later.erp.constants.SystemCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierCertificateService {
    private final SupplierCertificateRepo supplierCertificateRepo;
    private final SupplierRepo supplierRepo;
    private final CertificateTypeService certificateTypeService;
    private final SupplierDocumentService documentService;


    public List<SupplierCertificate> findAllDeleted(Long supplierId) throws ApiException {
        Supplier supplier = supplierRepo.findById(supplierId).orElse(null);
        if (supplier == null) {
            throw new ApiException(404, "Supplier not found");
        }
        return supplierCertificateRepo.findAllDeletedBySupplierId(supplierId);
    }

    public String create(SupplierCertificateCreationModel supplierCertificateCreationModel, Long supplierId) throws ApiException {
        Supplier supplier = supplierRepo.findById(supplierId).orElse(null);
        if (supplier == null) {
            throw new ApiException(404, "Supplier not found");
        }

        SupplierCertificate supplierCertificate = validateCreationModel(supplierCertificateCreationModel, null, supplier);
        List<SupplierDocument> document = null;
        if (supplierCertificateCreationModel.getAttachment() != null) {
            document = documentService
                    .uploadFromTemp(List.of(supplierCertificateCreationModel.getAttachment()),
                            SystemCode.SUPPLIER.systemCode());
        }
        supplierCertificate = supplierCertificateRepo.save(supplierCertificate);
        if (document != null) {
            documentService.saveDocument(document, supplierCertificate, "CERTIFICATE", true, SystemCode.SUPPLIER.systemCode());
        }
        return ResponseText.CREATED.text();
    }


    public String update(SupplierCertificateCreationModel supplierCertificateCreationModel, Long id) throws ApiException {
        SupplierCertificate oldSupplierCertificate = supplierCertificateRepo.findById(id).orElse(null);
        if (oldSupplierCertificate == null) {
            throw new ApiException(404, "Supplier certificate not found");
        }
        SupplierCertificate supplierCertificate = validateCreationModel(supplierCertificateCreationModel,
                oldSupplierCertificate, oldSupplierCertificate.getSupplier());
        List<SupplierDocument> document = null;
        if (supplierCertificateCreationModel.getAttachment() != null) {
            documentService.delete(oldSupplierCertificate.getDocuments(), SystemCode.SUPPLIER.systemCode());
            document = documentService
                    .uploadFromTemp(List.of(supplierCertificateCreationModel.getAttachment()),
                            SystemCode.SUPPLIER.systemCode());
        }
        supplierCertificate = supplierCertificateRepo.save(supplierCertificate);
        if (document != null) {
            documentService.saveDocument(document, supplierCertificate, "CERTIFICATE", true, SystemCode.SUPPLIER.systemCode());
        }
        return ResponseText.UPDATED.text();
    }

    private SupplierCertificate validateCreationModel(SupplierCertificateCreationModel supplierCertificateCreationModel,
                                                      SupplierCertificate oldSupplierCertificate, Supplier supplier) throws ApiException {
        CertificateType certificateType = certificateTypeService
                .findById(supplierCertificateCreationModel.getCertificateType());

        if (oldSupplierCertificate == null && supplierCertificateCreationModel.getAttachment() == null) {
            throw new ApiException(404, "Attachment is mandatory");
        }
        if (certificateType.getExpires() != null && certificateType.getExpires()) {
            if (supplierCertificateCreationModel.getExpiryDate() == null && oldSupplierCertificate == null) {
                throw new ApiException(404, "Supplier Certificate Expiry Date is mandatory");
            }
        }

        SupplierCertificate supplierCertificate = new SupplierCertificate();

        supplierCertificate.setCertificateTypeId(certificateType.getId());
        supplierCertificate.setCertificateTypeName(certificateType.getName());
        supplierCertificate.setCertificateTypeNameAr(certificateType.getNameAr());
        supplierCertificate.setCertificateTypeRequired(certificateType.getRequired());
        supplierCertificate.setSupplier(supplier);
        supplierCertificate.setExpiryDate(supplierCertificateCreationModel.getExpiryDate());
        if (oldSupplierCertificate != null) {
            supplierCertificate.setId(oldSupplierCertificate.getId());
            supplierCertificate.setCreationDate(oldSupplierCertificate.getCreationDate());
        }
        return supplierCertificate;
    }

    public String delete(Long id) throws ApiException {
        SupplierCertificate supplierCertificate = supplierCertificateRepo.findById(id).orElse(null);
        if (supplierCertificate == null) {
            throw new ApiException(404, "Supplier certificate not found");
        }
        if (supplierCertificate.getSupplier().getStatus().equals(Status.APPROVED.code())
                && supplierCertificate.getCertificateTypeRequired()) {
            throw new ApiException(404, "Certificate type is mandatory and can not be deleted");
        }
        documentService.delete(supplierCertificate.getDocuments(), SystemCode.SUPPLIER.systemCode());
        supplierCertificateRepo.delete(supplierCertificate);
        return "Deleted successfully";
    }

    public String retrieveDeleted(Long id) throws ApiException {
        SupplierCertificate supplierCertificate = supplierCertificateRepo.findDeletedById(id).orElse(null);
        if (supplierCertificate == null) {
            throw new ApiException(404, "Supplier certificate not found");
        }
        documentService.retrieveDeleted(supplierCertificate.getDocuments(), SystemCode.SUPPLIER.systemCode());
        supplierCertificateRepo.retrieve(supplierCertificate);
        return "Retrieved successfully";
    }

    public DocumentModel downloadCertificate(Long id, String fileName) throws ApiException {
        SupplierCertificate supplierCertificate = supplierCertificateRepo.findById(id).orElse(null);
        if (supplierCertificate == null) {
            throw new ApiException(404, "Supplier certificate not found");
        }
        Document doc = supplierCertificate.getDocuments().stream().filter(d -> d.getFileName().equals(fileName))
                .findFirst().orElse(null);
        if (doc == null) {
            throw new ApiException(404, "Document not found");
        }
        return documentService.downloadFileBase64(supplierCertificate.getDocuments().get(0).getFileName(),
                SystemCode.SUPPLIER.systemCode());

    }

    public SupplierCertificate createForBusinessType(SupplierCertificateCreationModel supplierCertificateCreationModel, Supplier supplier) throws ApiException {
        SupplierCertificate supplierCertificate = validateCreationModel(supplierCertificateCreationModel, null, supplier);
        supplierCertificateRepo.save(supplierCertificate);
        supplierCertificate.setSupportType(true);
        return supplierCertificate;
    }

    public SupplierCertificate updateForBusinessType(SupplierCertificateCreationModel supplierCertificateCreationModel,
                                                     SupplierCertificate oldSupplierCertificate, Supplier supplier) throws ApiException {
        SupplierCertificate supplierCertificate = validateCreationModel(supplierCertificateCreationModel,
                oldSupplierCertificate, supplier);
        supplierCertificate.setSupportType(true);
        supplierCertificateRepo.save(supplierCertificate);
        return supplierCertificate;
    }

    public void deleteForBusinessType(SupplierCertificate supplierCertificate) {
        documentService.delete(supplierCertificate.getDocuments(), SystemCode.SUPPLIER.systemCode());
        supplierCertificateRepo.delete(supplierCertificate);
    }

    public DocumentModel downloadCertificateForBusinessType(SupplierCertificate supplierCertificate) throws ApiException {
        return documentService.downloadFileBase64(supplierCertificate.getDocuments().get(0).getFileName(), SystemCode.SUPPLIER.systemCode());

    }
}
