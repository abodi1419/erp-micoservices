package com.later.inventoryservice.App.inventory.DocumentService.repository;


import com.later.inventoryservice.App.inventory.DocumentService.entity.InventoryDocument;
import com.later.inventoryservice.interfaces.SoftDeleteRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryDocumentRepo extends SoftDeleteRepo<InventoryDocument, Long> {
    @Query("select coalesce(max(x.serial), 0)+1 from InventoryDocument x where x.systemCode=:systemCode and x.refId=:refId" +
            " and x.systemPart=:systemPart ")
    Integer getCurrentSerial(String systemCode, Long refId, String systemPart);

    @Query("select d from InventoryDocument d where d.systemCode=:systemCode and d.systemPart=:systemPart and " +
            " d.refId=:refId and d.deleted=false ")
    List<InventoryDocument> findAllBySystemCodeAndSystemPartAndRefId(String systemCode, String systemPart, Long refId);
}
