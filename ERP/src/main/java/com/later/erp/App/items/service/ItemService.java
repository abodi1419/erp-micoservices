package com.later.erp.App.items.service;

import com.later.erp.App.company.employees.entity.Employee;
import com.later.erp.App.items.entity.Item;
import com.later.erp.App.items.entity.ItemCategory;
import com.later.erp.App.items.entity.ItemHistory;
import com.later.erp.App.items.entity.MeasureUnit;
import com.later.erp.App.items.model.ItemCreationModel;
import com.later.erp.App.items.repository.ItemCategoryRepo;
import com.later.erp.App.items.repository.ItemHistoryRepo;
import com.later.erp.App.items.repository.ItemRepo;
import com.later.erp.App.items.repository.MeasureUnitRepo;
import com.later.erp.CommonModules.discipline.entity.Discipline;
import com.later.erp.CommonModules.discipline.service.DisciplineService;
import com.later.erp.Exception.ApiException;
import com.later.erp.constants.ResponseText;
import com.later.erp.constants.UserActions;
import com.later.erp.util.RefCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepo itemRepo;
    private final ItemCategoryRepo itemCategoryRepo;
    private final MeasureUnitRepo measureUnitRepo;
    private final ItemHistoryRepo itemHistoryRepo;
    private final DisciplineService disciplineService;


    public List<Item> findAll() {
        return itemRepo.findAll();
    }

    public List<Item> findAllById(List<Long> ids) {
        return itemRepo.findAllById(ids);
    }

    public List<Item> findAllByIdUnderDiscipline(List<Long> ids, Long disciplineId) {
        return itemRepo.findAllByIdAndDiscipline_Id(ids, disciplineId);
    }

    public Item findById(Long id) throws ApiException {
        Item Item = itemRepo.findById(id).orElse(null);
        if (Item == null) {
            throw new ApiException(404, "Item not found");
        }
        return Item;
    }

    public String create(ItemCreationModel itemCreationModel, Employee login) throws ApiException {
        Item item = validateCreationModel(itemCreationModel, null, login);
        itemRepo.save(item);
        return "Created Successfully";
    }

    public Item validateCreationModel(ItemCreationModel itemCreationModel, Item oldItem, Employee login) throws ApiException {
        ItemCategory itemCategory = itemCategoryRepo.findById(itemCreationModel.getCategoryId()).orElse(null);
        MeasureUnit measureUnit = measureUnitRepo.findById(itemCreationModel.getMeasureUnit()).orElse(null);
        Discipline discipline = disciplineService.findById(itemCreationModel.getDiscipline());
        if (measureUnit == null) {
            throw new ApiException(404, "Measure Unit not found");
        }
        if (itemCategory == null) {
            throw new ApiException(404, "Item Category not found");
        }
        if (discipline == null) {
            throw new ApiException(404, "Discipline not found");
        }

        Item item = new Item();
        item.setName(itemCreationModel.getName());
        item.setNameAr(itemCreationModel.getNameAr());
        item.setDescription(itemCreationModel.getDescription());
        item.setDescriptionAr(itemCreationModel.getDescriptionAr());
        item.setItemCategory(itemCategory);
        item.setMeasureUnit(measureUnit);
        item.setBarcode(itemCreationModel.getBarcode());
        item.setImage(itemCreationModel.getImage());
        item.setService(itemCreationModel.getService());
        item.setReorderLevel(itemCreationModel.getReorderLevel());
        item.setReorderQuantity(itemCreationModel.getReorderQuantity());
        item.setDisciplineId(discipline.getId());
        item.setDisciplineName(discipline.getName());
        item.setDisciplineNameAr(discipline.getNameAr());
        item.setDisciplineCode(discipline.getCode());
        if (oldItem != null) {
            item.setId(oldItem.getId());
            item.setSerial(oldItem.getSerial());
            item.setRefCode(oldItem.getRefCode());
        } else {
            item.setSerial(itemRepo.getCurrentSerial());
            item.setRefCode(RefCode.generate("I", item.getSerial()));
        }
        return item;
    }

    public String update(Long id, ItemCreationModel itemCreationModel, Employee login) throws ApiException {
        Item oldItem = itemRepo.findById(id).orElse(null);
        if (oldItem == null) {
            throw new ApiException(404, "Item not found");
        }
        ItemHistory itemHistory = createHistory(oldItem, UserActions.UPDATE.action(), login.getId());
        Item item = validateCreationModel(itemCreationModel, oldItem, login);

        itemRepo.save(item);
        itemHistoryRepo.save(itemHistory);
        return ResponseText.UPDATED.text();
    }

    public ItemHistory createHistory(Item item, String action, Long actionBy) {
        ItemHistory itemHistory = new ItemHistory();
        itemHistory.setRefId(item.getId());
        itemHistory.setAction(action);
        itemHistory.setActionBy(actionBy);
        itemHistory.setName(item.getName());
        itemHistory.setNameAr(item.getNameAr());
        itemHistory.setDescription(item.getDescription());
        itemHistory.setDescriptionAr(item.getDescriptionAr());
        itemHistory.setImage(item.getImage());
        itemHistory.setProduct(item.getService());
        itemHistory.setBarcode(item.getBarcode());
        itemHistory.setSerial(item.getSerial());
        itemHistory.setRefCode(item.getRefCode());
        itemHistory.setItemCategory(item.getItemCategory().getId());
        itemHistory.setMeasureUnit(item.getMeasureUnit().getId());
        itemHistory.setAveragePrice(item.getAveragePrice());
        itemHistory.setLowestPrice(item.getLowestPrice());
        itemHistory.setHighestPrice(item.getHighestPrice());
        itemHistory.setDiscipline(item.getDisciplineId());
        return itemHistory;
    }

    public List<Item> findAllUnderDiscipline(Long disciplineId) {
        return itemRepo.findAllUnderDiscipline(disciplineId);
    }
}
