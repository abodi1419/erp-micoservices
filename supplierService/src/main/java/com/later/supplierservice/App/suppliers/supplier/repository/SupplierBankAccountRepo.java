package com.later.supplierservice.App.suppliers.supplier.repository;

import com.later.supplierservice.App.suppliers.supplier.entity.SupplierBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierBankAccountRepo extends JpaRepository<SupplierBankAccount, Long> {
    @Query("select coalesce(max(b.preferred), 0)+1 from SupplierBankAccount b")
    Short getCurrentPreferred();

    @Query("select coalesce(count(a.id),0) from SupplierBankAccount a where a.supplier.id=:supplierId")
    Integer countBySupplierId(Long supplierId);
}
