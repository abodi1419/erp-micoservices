package com.later.erp.App.suppliers.supplier.repository;

import com.later.erp.App.suppliers.supplier.entity.SupplierContact;
import com.later.erp.App.suppliers.supplier.model.SupplierContactListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierContactRepo extends JpaRepository<SupplierContact, Long> {
    @Query("select coalesce(max(c.preferred), 0)+1 from SupplierContact c")
    Short getCurrentPreferred();

    @Query("select coalesce(count(c.id),0) from SupplierContact c where c.supplier.id=:supplierId")
    Integer countBySupplierId(Long supplierId);

    List<SupplierContact> findAllBySupplier_Id(Long supplierId);


    @Query("select new com.later.erp.App.suppliers.supplier.model.SupplierContactListModel(sc.id, sc.contactName, sc.contactNameAr, " +
            "sc.contactJobTitle, sc.contactJobTitleAr,sc.contactEmail, sc.contactPhone, " +
            "CONCAT(sc.cityName,' - ', sc.cityNameAr), sc.preferred ) from SupplierContact sc " +
            "where sc.supplier.id=:supplierId order by sc.id desc ")
    List<SupplierContactListModel> listBySupplierId(Long supplierId);


}
