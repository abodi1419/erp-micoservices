package com.later.erp.App.suppliers.cashSupplier.service;

import com.later.erp.App.suppliers.cashSupplier.entity.CashSupplier;
import com.later.erp.App.suppliers.cashSupplier.entity.CashSupplierContact;
import com.later.erp.App.suppliers.cashSupplier.model.CashSupplierListModel;
import com.later.erp.App.suppliers.cashSupplier.repository.CashSupplierContactRepo;
import com.later.erp.App.suppliers.cashSupplier.repository.CashSupplierRepo;
import com.later.erp.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CashSupplierService {

    private final CashSupplierRepo cashSupplierRepo;
    private final CashSupplierContactRepo cashSupplierContactRepo;

    public CashSupplier findByIdForOtherSystems(Long supplierId) throws ApiException {
        CashSupplier cashSupplier = cashSupplierRepo.findById(supplierId).orElse(null);
        if (cashSupplier == null) {
            throw new ApiException(404, "Supplier not found");
        }
        return cashSupplier;
    }

    public CashSupplierContact findContactByIdForOtherSystems(Long supplierContact, CashSupplier cashSupplier) throws ApiException {
        CashSupplierContact cashSupplierContact = cashSupplierContactRepo.findById(supplierContact).orElse(null);
        if (cashSupplierContact == null || !cashSupplierContact.getSupplier().getId().equals(cashSupplier.getId())) {
            throw new ApiException(404, "Supplier contact not found");
        }
        return cashSupplierContact;
    }

    public List<CashSupplierListModel> findAllForOtherSystems() {
        return cashSupplierRepo.listAllCashSuppliers();
    }
}
