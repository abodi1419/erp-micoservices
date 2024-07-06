package com.later.erp.App.inventory.stocks.stock.service;


import com.later.erp.App.inventory.stocks.approvalService.service.StockApprovalService;
import com.later.erp.App.inventory.stocks.stock.entity.Stock;
import com.later.erp.App.inventory.stocks.stock.model.StockCreationModel;
import com.later.erp.App.inventory.stocks.stock.repository.StockRepo;
import com.later.erp.App.inventory.warehouses.entity.Warehouse;
import com.later.erp.App.inventory.warehouses.entity.WarehouseDetails;
import com.later.erp.App.inventory.warehouses.repository.WarehouseDetailsRepo;
import com.later.erp.App.inventory.warehouses.repository.WarehouseRepo;
import com.later.erp.App.items.entity.Item;
import com.later.erp.App.items.service.ItemService;
import com.later.erp.Authorization.Security.Auth.entities.Employee;
import com.later.erp.CommonServices.DocumentService.service.DocumentService;
import com.later.erp.Exception.ApiException;
import com.later.erp.constants.ResponseText;
import com.later.erp.constants.SystemCode;
import com.later.erp.util.RefCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServices {

    private final StockRepo stockRepo;
    private final WarehouseRepo warehouseRepo;
    private final WarehouseDetailsRepo warehouseDetailsRepo;

    private final ItemService itemService;
    private final StockApprovalService stockApprovalService;
    private final DocumentService documentService;

    public List<Stock> findAll() {
        return stockRepo.findAll();
    }

    public Stock findById(Long stockId, Employee employee) throws ApiException {
        Stock stock = stockRepo.findById(stockId).orElse(null);
        if (stock == null || (stock.getCreatedById().equals(employee.getId())
                && stock.getApproval().stream()
                .noneMatch(a -> a.getApproverId().equals(employee.getId()))
        )) {
            throw new ApiException(404, "Purchase request not found");
        }
        return stock;
    }

    public List<Item> getItems() {
        return itemService.findAll();
    }

    public List<Warehouse> getWarehouses() {
        return warehouseRepo.findAll();
    }

    public List<WarehouseDetails> getWarehouseDetails(Long warehouseId) throws ApiException {
        Warehouse warehouse = warehouseRepo.findById(warehouseId).orElse(null);
        if (warehouse == null)
            throw new ApiException(404, "Warehouse not found");

        return warehouseDetailsRepo.findByWarehouseId(warehouse.getId());
    }

    public String create(StockCreationModel stockCreationModel) throws ApiException {
        validateTempCreationModel(stockCreationModel, null);
        return ResponseText.CREATED.text();
    }

    public String update(StockCreationModel stockCreationModel, Long id) throws ApiException {
        Stock oldStock = stockRepo.findById(id).orElse(null);
        if (oldStock == null)
            throw new ApiException(404, "Stock not found");

        validateTempCreationModel(stockCreationModel, oldStock);
        return ResponseText.UPDATED.text();
    }

    private void validateTempCreationModel(StockCreationModel stockCreationModel, Stock oldStock) throws ApiException {
        Stock stock = new Stock();

        Warehouse warehouse = warehouseRepo.findById(stockCreationModel.getWarehouseId()).orElse(null);
        Item item = itemService.findById(stockCreationModel.getItemId());
        WarehouseDetails warehouseDetails = warehouseDetailsRepo.findById(stockCreationModel.getWarehouseDetailsId()).orElse(null);

        if (item == null)
            throw new ApiException(404, "warehouse not found");

        if (warehouse == null)
            throw new ApiException(404, "warehouse not found");

        if (warehouseDetails == null)
            throw new ApiException(404, "warehouse not found");

        stock.setItemId(item.getId());
        stock.setItemRefCode(item.getRefCode());
        stock.setItemDescription(item.getDescription());
        stock.setItemDescriptionAr(item.getDescriptionAr());
        stock.setItemName(item.getName());
        stock.setItemNameAr(item.getNameAr());
        stock.setServiceItem(item.getService());
        stock.setUnitOfMeasureId(item.getMeasureUnit().getId());
        stock.setUnitOfMeasureCode(item.getMeasureUnit().getCode());
        stock.setUnitOfMeasureCodeAr(item.getMeasureUnit().getCodeAr());
        stock.setUnitOfMeasureName(item.getMeasureUnit().getName());
        stock.setUnitOfMeasureNameAr(item.getMeasureUnit().getNameAr());
        stock.setWarehouse(warehouse);
        stock.setWarehouseDetails(warehouseDetails);
        stock.setQuantity(stockCreationModel.getQuantity());
        stock.setStatus((short) 0);

        if (oldStock == null) {
            stock.setSerial(stockRepo.getCurrentSerial());
            stock.setRefCode(RefCode.generate(SystemCode.STOCK.systemCode(), stock.getSerial()));
        } else {
            stock.setId(oldStock.getId());
            stock.setSerial(oldStock.getSerial());
            stock.setRefCode(oldStock.getRefCode());
        }
        stockRepo.save(stock);
    }

    public String approve(Long stockId, HashMap<String, String> map, Employee loginUser) throws ApiException {
        Stock stock = stockRepo.findById(stockId).orElse(null);
        if (stock == null)
            throw new ApiException(404, "Stock not found");

        stock = (Stock) stockApprovalService.approve(stock, SystemCode.STOCK.systemCode(), loginUser, map.getOrDefault("comment", null));
        stockRepo.save(stock);
        return ResponseText.APPROVED.text();
    }

    public String reject(Long stockId, HashMap<String, String> map, Employee loginUser) throws ApiException {
        Stock stock = stockRepo.findById(stockId).orElse(null);
        if (stock == null)
            throw new ApiException(404, "Stock not found");

        stock = (Stock) stockApprovalService.reject(stock, SystemCode.SUPPLIER.systemCode(), loginUser, map.getOrDefault("comment", null));
        stockRepo.save(stock);

        return ResponseText.REJECTED.text();
    }

//
//    public DocumentModel downloadAttachment(Long id, String attachmentName, Employee loginUser) throws ApiException {
//        Stock stock = findById(id,loginUser);
//        if (stock.getDocuments().stream().anyMatch(a -> a.getFileName().equals(attachmentName))) {
//            return documentService.downloadFileBase64(attachmentName, SystemCode.STOCK.systemCode());
//        }
//        throw new ApiException(404, "File not found");
//    }
//

}
