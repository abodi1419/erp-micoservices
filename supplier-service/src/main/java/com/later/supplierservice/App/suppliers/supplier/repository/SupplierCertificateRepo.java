package com.later.supplierservice.App.suppliers.supplier.repository;

import com.later.supplierservice.App.suppliers.supplier.entity.SupplierCertificate;
import com.later.supplierservice.interfaces.SoftDeleteRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierCertificateRepo extends SoftDeleteRepo<SupplierCertificate, Long> {
    @Query("select s from SupplierCertificate s where s.supplier.id=:supplierId and s.deleted=true ")
    List<SupplierCertificate> findAllDeletedBySupplierId(Long supplierId);
}
