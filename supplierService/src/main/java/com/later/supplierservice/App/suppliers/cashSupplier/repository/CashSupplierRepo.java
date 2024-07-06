package com.later.supplierservice.App.suppliers.cashSupplier.repository;

import com.later.supplierservice.App.suppliers.cashSupplier.entity.CashSupplier;
import com.later.supplierservice.App.suppliers.cashSupplier.model.CashSupplierListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashSupplierRepo extends JpaRepository<CashSupplier, Long> {

    @Query("select new com.later.supplierservice.App.suppliers.cashSupplier.model.CashSupplierListModel(sp.id, sp.companyName, sp.companyNameAr, " +
            "CONCAT(sp.currencyName,' - ',sp.currencyNameAr), sp.vatNumber, sp.address, sp.refCode," +
            "sp.createdAt) from CashSupplier sp order by sp.id desc ")
    List<CashSupplierListModel> listAllCashSuppliers();
//    Long id, String companyName, String companyNameAr, String preferredCurrency,
//                                 String vatNumber, String address, String refCode, LocalDateTime createdAt

}
