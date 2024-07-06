package com.later.erp.App.suppliers.cashSupplier.repository;

import com.later.erp.App.suppliers.cashSupplier.entity.CashSupplierContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashSupplierContactRepo extends JpaRepository<CashSupplierContact, Long> {
}
