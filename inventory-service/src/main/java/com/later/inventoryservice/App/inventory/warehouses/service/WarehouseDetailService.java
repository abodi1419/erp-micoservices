package com.later.inventoryservice.App.inventory.warehouses.service;

import com.later.inventoryservice.App.inventory.warehouses.entity.Warehouse;
import com.later.inventoryservice.App.inventory.warehouses.entity.WarehouseDetails;
import com.later.inventoryservice.App.inventory.warehouses.entity.WarehouseDetailsHistory;
import com.later.inventoryservice.App.inventory.warehouses.model.WarehouseDetailsCreationModel;
import com.later.inventoryservice.App.inventory.warehouses.repository.WarehouseDetailsHistoryRepo;
import com.later.inventoryservice.App.inventory.warehouses.repository.WarehouseDetailsRepo;
import com.later.inventoryservice.App.inventory.warehouses.repository.WarehouseRepo;
import com.later.inventoryservice.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseDetailService {
    private final WarehouseDetailsRepo warehouseDetailsRepo;
    private final WarehouseDetailsHistoryRepo warehouseDetailsHistoryRepo;
    private final WarehouseRepo warehouseRepo;

    public String creationWareHouseDetails(Long wareHouseId, List<WarehouseDetailsCreationModel> warehouseDetailsCreationModelList) throws ApiException {
        Warehouse warehouse = warehouseRepo.findById(wareHouseId).orElse(null);
        if (warehouse == null) {
            throw new ApiException(404, "ware house type not found");
        }

        List<WarehouseDetails> warehouseDetailsList = new ArrayList<>();

        for (WarehouseDetailsCreationModel w : warehouseDetailsCreationModelList) {
            WarehouseDetails warehouseDetails = getWarehouseDetails(w, warehouse);
            warehouseDetailsList.add(warehouseDetails);
        }

        warehouseDetailsRepo.saveAll(warehouseDetailsList);
//        warehouse.setWarehouseDetails(warehouseDetailsList);
//        warehouseRepo.save(warehouse);
        return "Created Ware House Details Successfully";
    }


    public String updateWareHouseDetails(Long wareHouseId, List<WarehouseDetailsCreationModel> warehouseDetailsCreationModelList) throws ApiException {
        Warehouse warehouse = warehouseRepo.findById(wareHouseId).orElse(null);
        if (warehouse == null) {
            throw new ApiException(404, "ware house type not found");
        }

        List<WarehouseDetailsHistory> warehouseDetailsHistoryList = new ArrayList<>();
        for (WarehouseDetailsCreationModel w : warehouseDetailsCreationModelList) {
            if (w.getId() == null) {
                WarehouseDetails warehouseDetails = getWarehouseDetails(w, warehouse);
                warehouse.getWarehouseDetails().add(warehouseDetails);
            } else {
                for (WarehouseDetails a : warehouse.getWarehouseDetails()) {
                    if (a.getId().equals(w.getId())) {
                        WarehouseDetailsHistory warehouseDetailsHistory = createWarehouseDetailsHistory(a, "update", 1L);
                        warehouseDetailsHistoryList.add(warehouseDetailsHistory);
                        a.setName(w.getName());
                        a.setNameAr(w.getNameAr());
                        a.setDescription(w.getDescription());
                        a.setDescriptionAr(w.getDescriptionAr());
                    }
                }
            }
        }

        warehouseDetailsRepo.saveAll(warehouse.getWarehouseDetails());
        warehouseDetailsHistoryRepo.saveAll(warehouseDetailsHistoryList);
        return "Update Ware House Details Successfully";

    }


    public WarehouseDetailsHistory createWarehouseDetailsHistory(WarehouseDetails warehouseDetails, String action, Long actionBy) {
        WarehouseDetailsHistory warehouseDetailsHistory = new WarehouseDetailsHistory();
        warehouseDetailsHistory.setName(warehouseDetails.getName());
        warehouseDetailsHistory.setNameAr(warehouseDetails.getNameAr());
        warehouseDetailsHistory.setDescription(warehouseDetails.getDescription());
        warehouseDetailsHistory.setDescriptionAr(warehouseDetails.getDescriptionAr());
        warehouseDetailsHistory.setWarehouseId(warehouseDetails.getWarehouse().getId());
        warehouseDetailsHistory.setAction(action);
        warehouseDetailsHistory.setActionBy(actionBy);
        return warehouseDetailsHistory;
    }


    private static WarehouseDetails getWarehouseDetails(WarehouseDetailsCreationModel w, Warehouse warehouse) {
        WarehouseDetails warehouseDetails = new WarehouseDetails();
        warehouseDetails.setName(w.getName());
        warehouseDetails.setNameAr(w.getNameAr());
        warehouseDetails.setDescription(w.getDescription());
        warehouseDetails.setDescriptionAr(w.getDescriptionAr());
        warehouseDetails.setWarehouse(warehouse);
        return warehouseDetails;
    }


}
