package com.later.supplierservice.App.suppliers.supplier.repository;

import com.later.supplierservice.App.suppliers.supplier.entity.SupplierBusinessType;
import com.later.supplierservice.App.suppliers.supplier.entity.SupplierCertificate;
import com.later.supplierservice.interfaces.SoftDeleteRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierBusinessTypeRepo extends SoftDeleteRepo<SupplierBusinessType, Long> {
    @Query("select s from SupplierBusinessType s where s.supplier.id=:supplierId and s.deleted=true ")
    List<SupplierCertificate> findAllDeletedBySupplierId(Long supplierId);

    @Query("select coalesce(count(c.id),0) from SupplierBusinessType c where c.supplier.id=:supplierId")
    Integer countBySupplierId(Long supplierId);
}
