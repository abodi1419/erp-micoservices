package com.later.supplierservice.App.suppliers.supplier.repository;

import com.later.supplierservice.App.suppliers.supplier.entity.Supplier;
import com.later.supplierservice.App.suppliers.supplier.model.SupplierListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Long> {
    @Query("select coalesce(max(x.serial), 0)+1 from Supplier x")
    Integer getCurrentSerial();

    //    Long id, String companyName, String companyNameAr, IndustryType primaryIndustry,
//                             String size, String refCode, Boolean local, Short status, Short statusDesc

    @Query("select distinct new com.later.supplierservice.App.suppliers.supplier.model.SupplierListModel(sp.id, sp.companyName, " +
            "sp.companyNameAr, CONCAT(sp.industryTypeName,' - ',sp.industryTypeNameAr), " +
            "sp.size, sp.refCode, sp.local, sp.status, CONCAT(sp.currencyName, ' - ', sp.currencyNameAr)) " +
            " from Supplier sp inner join SupplierActiveApproval sa on sa.refId=sp.id and " +
            "sa.status=sp.status and sa.approverId=:employeeId " +
            "order by sp.id")
    List<SupplierListModel> waitingMyAction(Long employeeId);

    @Query("select distinct new com.later.supplierservice.App.suppliers.supplier.model.SupplierListModel(sp.id, sp.companyName, " +
            "sp.companyNameAr, CONCAT(sp.industryTypeName,' - ',sp.industryTypeNameAr), " +
            "sp.size, sp.refCode, sp.local, sp.status, CONCAT(sp.currencyName, ' - ', sp.currencyNameAr))  " +
            " from Supplier sp left join SupplierActiveApproval sa on sa.refId=sp.id " +
            " where sp.status>0 and sp.status<50 and (sp.createdById=:employeeId or :employeeId in (sa.approverId) ) " +
            "order by sp.id")
    List<SupplierListModel> active(Long employeeId);

    @Query("select distinct new com.later.supplierservice.App.suppliers.supplier.model.SupplierListModel(sp.id, sp.companyName, " +
            "sp.companyNameAr, CONCAT(sp.industryTypeName,' - ',sp.industryTypeNameAr), " +
            "sp.size, sp.refCode, sp.local, sp.status, CONCAT(sp.currencyName, ' - ', sp.currencyNameAr))  " +
            " from Supplier sp left join SupplierActiveApproval sa on sa.refId=sp.id " +
            " where sp.status>=50 and (sp.createdById=:employeeId or :employeeId in (sa.approverId) )" +
            "order by sp.id")
    List<SupplierListModel> closed(Long employeeId);

    @Query("select distinct new com.later.supplierservice.App.suppliers.supplier.model.SupplierListModel(sp.id, sp.companyName, " +
            "sp.companyNameAr, CONCAT(sp.industryTypeName,' - ',sp.industryTypeNameAr), " +
            "sp.size, sp.refCode, sp.local, sp.status, CONCAT(sp.currencyName, ' - ', sp.currencyNameAr))  " +
            " from Supplier sp where sp.createdById=:employeeId " +
            "order by sp.id")
    List<SupplierListModel> myRequests(Long employeeId);


    @Query("select distinct new com.later.supplierservice.App.suppliers.supplier.model.SupplierListModel(sp.id, sp.companyName, " +
            "sp.companyNameAr, CONCAT(sp.industryTypeName,' - ',sp.industryTypeNameAr), " +
            "sp.size, sp.refCode, sp.local, sp.status, CONCAT(sp.currencyName, ' - ', sp.currencyNameAr))  " +
            " from Supplier sp where sp.active=true " +
            " order by sp.id ")
    List<SupplierListModel> findAllActive();
}
