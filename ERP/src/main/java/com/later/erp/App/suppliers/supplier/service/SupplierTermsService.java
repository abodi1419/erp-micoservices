package com.later.erp.App.suppliers.supplier.service;

import com.later.erp.App.suppliers.supplier.entity.Supplier;
import com.later.erp.App.suppliers.supplier.entity.SupplierTerm;
import com.later.erp.App.suppliers.supplier.model.validation.SupplierTermCreationModel;
import com.later.erp.App.suppliers.supplier.repository.SupplierRepo;
import com.later.erp.App.suppliers.supplier.repository.SupplierTermRepo;
import com.later.erp.CommonModules.termsTypes.entity.TermType;
import com.later.erp.CommonModules.termsTypes.service.TermTypeService;
import com.later.erp.Exception.ApiException;
import com.later.erp.constants.ResponseText;
import com.later.erp.constants.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierTermsService {
    private final SupplierRepo supplierRepo;
    private final SupplierTermRepo supplierTermRepo;
    private final TermTypeService termTypeService;

    public String create(SupplierTermCreationModel supplierTermCreationModel, Long supplierId) throws ApiException {
        Supplier supplier = supplierRepo.findById(supplierId).orElse(null);
        if (supplier == null) {
            throw new ApiException(404, "Supplier not found");
        }
        SupplierTerm supplierTerm = validateCreationModel(supplierTermCreationModel, null, supplier);
        supplierTermRepo.save(supplierTerm);
        return ResponseText.CREATED.text();
    }

    private SupplierTerm validateCreationModel(SupplierTermCreationModel supplierTermCreationModel,
                                               SupplierTerm oldSupplierTerm, Supplier supplier) throws ApiException {
        TermType termType = termTypeService.findById(supplierTermCreationModel.getTermType());
        SupplierTerm supplierTerm = new SupplierTerm();
        supplierTerm.setTermTypeId(termType.getId());
        supplierTerm.setTermTypeName(termType.getTermName());
        supplierTerm.setTermTypeNameAr(termType.getTermNameAr());
        supplierTerm.setSupplier(supplier);
        supplierTerm.setTerm(supplierTermCreationModel.getTerm());
        supplierTerm.setTermAr(supplierTermCreationModel.getTermAr());
        if (oldSupplierTerm != null) {
            supplierTerm.setId(oldSupplierTerm.getId());
            supplierTerm.setCreationDate(oldSupplierTerm.getCreationDate());
        }
        return supplierTerm;
    }

    public String update(SupplierTermCreationModel supplierTermCreationModel, Long id) throws ApiException {
        SupplierTerm oldSupplierTerm = supplierTermRepo.findById(id).orElse(null);
        if (oldSupplierTerm == null) {
            throw new ApiException(404, "Supplier terms not found");
        }
        SupplierTerm supplierTerm = validateCreationModel(supplierTermCreationModel, oldSupplierTerm,
                oldSupplierTerm.getSupplier());
        supplierTermRepo.save(supplierTerm);
        return ResponseText.UPDATED.text();

    }

    public String delete(Long id) throws ApiException {
        SupplierTerm supplierTerm = supplierTermRepo.findById(id).orElse(null);
        if (supplierTerm == null) {
            throw new ApiException(404, "Supplier term not found");
        }
        if (supplierTerm.getSupplier().getStatus().equals(Status.APPROVED.code())) {
            Integer count = supplierTermRepo.countBySupplierIdAndTermType(supplierTerm.getSupplier().getId(),
                    supplierTerm.getTermTypeId());
            if (count <= 1) {
                throw new ApiException(404, "Supplier terms cannot be deleted, at least one term must be present");
            }
        }
        supplierTermRepo.delete(supplierTerm);

        return ResponseText.DELETED.text();
    }
}
