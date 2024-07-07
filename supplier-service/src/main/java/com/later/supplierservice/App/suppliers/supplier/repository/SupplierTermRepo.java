package com.later.supplierservice.App.suppliers.supplier.repository;

import com.later.supplierservice.App.suppliers.supplier.entity.SupplierTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierTermRepo extends JpaRepository<SupplierTerm, Long> {

    @Query("select coalesce(count(c.id),0) from SupplierTerm c where c.supplier.id=:supplierId and c.termTypeId=:termTypeId")
    Integer countBySupplierIdAndTermType(Long supplierId, Long termTypeId);
}
