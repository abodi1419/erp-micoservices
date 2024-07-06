package com.later.erp.App.inventory.warehouses.service;

import com.later.erp.App.inventory.warehouses.entity.*;
import com.later.erp.App.inventory.warehouses.model.WarehouseCreationModel;
import com.later.erp.App.inventory.warehouses.model.WarehouseDetailsCreationModel;
import com.later.erp.App.inventory.warehouses.repository.*;
import com.later.erp.CommonModules.addresses.entity.City;
import com.later.erp.CommonModules.addresses.model.CityModel;
import com.later.erp.CommonModules.addresses.service.AddressService;
import com.later.erp.CommonModules.company.costCenter.entity.CostCenter;
import com.later.erp.CommonModules.company.costCenter.service.CostCenterService;
import com.later.erp.CommonModules.warehouseType.entity.WarehouseType;
import com.later.erp.CommonModules.warehouseType.service.WarehouseTypeService;
import com.later.erp.Exception.ApiException;
import com.later.erp.constants.SystemCode;
import com.later.erp.util.RefCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepo warehouseRepo;
    private final WarehouseDetailsRepo warehouseDetailsRepo;
    private final WarehouseHistoryRepo warehouseHistoryRepo;
    private final WarehouseTypeRelationRepo warehouseTypeRelationRepo;
    private final WarehouseDetailsHistoryRepo warehouseDetailsHistoryRepo;
    private final AddressService addressService;
    private final WarehouseTypeService warehouseTypeService;
    private final CostCenterService costCenterService;


    public List<Warehouse> findAll() {
        return warehouseRepo.findAll();
    }

    public Warehouse findById(Long id) throws ApiException {
        Warehouse warehouse = warehouseRepo.findById(id).orElse(null);
        if (warehouse == null)
            throw new ApiException(404, "Warehouse not found");

        return warehouse;
    }

    public List<CityModel> getCityList() {
        return addressService.findAllCitiesModel();
    }

    public List<CostCenter> getCostCenterList() {
        return costCenterService.findAll();
    }

    public String create(WarehouseCreationModel creationModel) throws ApiException {
        City city = addressService.findCityById(creationModel.getCityId());

        List<WarehouseType> warehouseTypeList = warehouseTypeService.findAllById(creationModel.getWarehouseTypeIds());
        if (warehouseTypeList.isEmpty()) {
            throw new ApiException(404, "ware house type not found");
        }

        Warehouse warehouse = new Warehouse();
        warehouse.setSerial(warehouseRepo.getCurrentSerial());
        warehouse.setRefCode(RefCode.generate(SystemCode.WAREHOUSE.systemCode(), warehouse.getSerial()));
        warehouse.setName(creationModel.getName());
        warehouse.setNameAr(creationModel.getNameAr());
        warehouse.setAddress(creationModel.getAddress());
        warehouse.setAddressAr(creationModel.getAddressAr());
        warehouse.setCityId(city.getId());
        warehouse.setCityName(city.getNameEn());
        warehouse.setCityNameAr(city.getNameAr());
        List<WarehouseTypeRelation> warehouseTypeRelations = new ArrayList<>();
        for (WarehouseType warehouseType : warehouseTypeList) {
            WarehouseTypeRelation warType = new WarehouseTypeRelation();
            warType.setWarehouseTypeId(warehouseType.getId());
            warType.setWarehouseTypeName(warehouseType.getName());
            warType.setWarehouseTypeNameAr(warehouseType.getNameAr());
            warType.setAllowSales(warehouseType.getAllowSales());
            warehouseTypeRelations.add(warType);
        }
        warehouse.setWarehouseTypeRelations(warehouseTypeRelations);
        warehouseRepo.save(warehouse);
        warehouseTypeRelationRepo.saveAll(warehouseTypeRelations);
        return "Created Successfully";
    }

    public String update(Long id, WarehouseCreationModel creationModel) throws ApiException {
        Warehouse warehouse = warehouseRepo.findById(id).orElse(null);
        if (warehouse == null) {
            throw new ApiException(404, "Warehouse not found");
        }
        City city = addressService.findCityById(creationModel.getCityId());
        List<WarehouseType> warehouseTypeList = warehouseTypeService.findAllById(creationModel.getWarehouseTypeIds());
        if (warehouseTypeList.isEmpty()) {
            throw new ApiException(404, "ware house type not found");
        }
        WarehouseHistory warehouseHistory = createWarehouseHistory(warehouse, "update", 1L);
        warehouse.setName(creationModel.getName());
        warehouse.setNameAr(creationModel.getNameAr());
        warehouse.setAddress(creationModel.getAddress());
        warehouse.setAddressAr(creationModel.getAddressAr());
        warehouse.setCityId(city.getId());
        warehouse.setCityNameAr(city.getNameAr());
        warehouse.setCityName(city.getNameEn());
        List<WarehouseTypeRelation> warehouseTypeRelations = new ArrayList<>();
        for (WarehouseType warehouseType : warehouseTypeList) {
            WarehouseTypeRelation warType = new WarehouseTypeRelation();
            warType.setWarehouseTypeId(warehouseType.getId());
            warType.setWarehouseTypeName(warehouseType.getName());
            warType.setWarehouseTypeNameAr(warehouseType.getNameAr());
            warType.setAllowSales(warehouseType.getAllowSales());
            warType.setWarehouse(warehouse);
            warehouseTypeRelations.add(warType);
        }
        warehouse.setWarehouseTypeRelations(warehouseTypeRelations);
        warehouseTypeRelationRepo.deleteAllByWarehouse_Id(warehouse.getId());
        warehouseTypeRelationRepo.saveAll(warehouseTypeRelations);
        warehouseRepo.save(warehouse);
        warehouseHistoryRepo.save(warehouseHistory);
        return "Update Successfully";
    }


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
            for (WarehouseDetails a : warehouse.getWarehouseDetails()) {
                if (w.getId() == null) {
                    WarehouseDetails warehouseDetails = getWarehouseDetails(w, warehouse);
                    warehouse.getWarehouseDetails().add(warehouseDetails);
                } else if (a.getId().equals(w.getId())) {
                    WarehouseDetailsHistory warehouseDetailsHistory = createWarehouseDetailsHistory(a, "update", 1L);
                    warehouseDetailsHistoryList.add(warehouseDetailsHistory);
                    a.setName(w.getName());
                    a.setNameAr(w.getNameAr());
                    a.setDescription(w.getDescription());
                    a.setDescriptionAr(w.getDescriptionAr());
                }
            }
        }

        warehouseDetailsRepo.saveAll(warehouse.getWarehouseDetails());
        warehouseDetailsHistoryRepo.saveAll(warehouseDetailsHistoryList);
        return "Update Ware House Details Successfully";

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

    public WarehouseHistory createWarehouseHistory(Warehouse warehouse, String action, Long actionBy) {
        WarehouseHistory warehouseHistory = new WarehouseHistory();
        warehouseHistory.setName(warehouse.getName());
        warehouseHistory.setNameAr(warehouse.getNameAr());
        warehouseHistory.setAddress(warehouse.getAddress());
        warehouseHistory.setAddressAr(warehouse.getAddressAr());
        warehouseHistory.setCityId(warehouse.getCityId());
        warehouseHistory.setRefCode(warehouse.getRefCode());
        if (warehouse.getWarehouseTypeRelations() != null || warehouse.getWarehouseTypeRelations().isEmpty())
            warehouseHistory.setWarehouseTypesId(warehouse.getWarehouseTypeRelations().stream().map(WarehouseTypeRelation::getWarehouseTypeId).toList().toString());

        warehouseHistory.setAction(action);
        warehouseHistory.setActionBy(actionBy);
        return warehouseHistory;
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


}
