package com.later.procurement.App.suppliers.cashSupplier.service;


import com.later.procurement.App.suppliers.cashSupplier.entity.CashSupplier;
import com.later.procurement.App.suppliers.cashSupplier.entity.CashSupplierContact;
import com.later.procurement.App.suppliers.cashSupplier.model.CashSupplierListModel;
import com.later.procurement.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CashSupplierService {


    public CashSupplier findByIdForOtherSystems(Long supplierId) throws ApiException {
       return null;
    }

    public CashSupplierContact findContactByIdForOtherSystems(Long supplierContact, CashSupplier cashSupplier) throws ApiException {
        return null;
    }

    public List<CashSupplierListModel> findAllForOtherSystems() {
        return List.of();
    }
}
