package com.later.procurement.App.suppliers.supplier.service;


import com.later.procurement.App.suppliers.supplier.entity.Supplier;
import com.later.procurement.App.suppliers.supplier.model.SupplierListModel;
import com.later.procurement.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    public Supplier findByIdForOtherSystems(Long supplierId) throws ApiException {

        return null;
    }

    public List<SupplierListModel> findAllForOtherSystems() {
        return List.of();
    }
}
