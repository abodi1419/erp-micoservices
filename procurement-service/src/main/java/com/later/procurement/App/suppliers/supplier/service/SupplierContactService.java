package com.later.procurement.App.suppliers.supplier.service;


import com.later.procurement.App.suppliers.supplier.entity.Supplier;
import com.later.procurement.App.suppliers.supplier.entity.SupplierContact;
import com.later.procurement.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierContactService {
    public SupplierContact findByIdForOtherSystems(Long supplierContactId, Supplier supplier) throws ApiException {
        return null;
    }

    public List<SupplierContact> findAllBySupplierIdForOtherSystems(Long supplierId) {
        return List.of();
    }
}
