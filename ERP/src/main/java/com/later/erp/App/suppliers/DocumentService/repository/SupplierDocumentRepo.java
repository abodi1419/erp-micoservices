package com.later.erp.App.suppliers.DocumentService.repository;

import com.later.erp.App.suppliers.DocumentService.entity.SupplierDocument;
import com.later.erp.interfaces.SoftDeleteRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierDocumentRepo extends SoftDeleteRepo<SupplierDocument, Long> {
    @Query("select coalesce(max(x.serial), 0)+1 from ProcurementDocument x where x.systemCode=:systemCode and x.refId=:refId" +
            " and x.systemPart=:systemPart ")
    Integer getCurrentSerial(String systemCode, Long refId, String systemPart);

    @Query("select d from ProcurementDocument d where d.systemCode=:systemCode and d.systemPart=:systemPart and " +
            " d.refId=:refId and d.deleted=false ")
    List<SupplierDocument> findAllBySystemCodeAndSystemPartAndRefId(String systemCode, String systemPart, Long refId);
}
