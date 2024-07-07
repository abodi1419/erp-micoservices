package com.later.itemservice.App.items.service;

import com.later.itemservice.App.items.entity.ItemCategory;
import com.later.itemservice.App.items.model.ItemCategoryCreationModel;
import com.later.itemservice.App.items.repository.ItemCategoryRepo;
import com.later.itemservice.Exception.ApiException;
import com.later.itemservice.constants.ResponseText;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemCategoryService {

    private final ItemCategoryRepo itemCategoryRepo;

    public List<ItemCategory> findAll() {
        return itemCategoryRepo.findAll();
    }

    public ItemCategory findById(Long id) throws ApiException {
        ItemCategory itemCategory = itemCategoryRepo.findById(id).orElse(null);
        if (itemCategory == null) {
            throw new ApiException(404, "Item category not found");
        }
        return itemCategory;
    }

    public String create(ItemCategoryCreationModel itemCategoryCreationModel) throws ApiException {
        ItemCategory parentCategory = null;
        if (itemCategoryCreationModel.getParentId() != null) {
            parentCategory = itemCategoryRepo.findById(itemCategoryCreationModel.getParentId()).orElse(null);
            if (parentCategory == null) {
                throw new ApiException(404, "Parent category not found");
            }
        }
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setName(itemCategoryCreationModel.getName());
        itemCategory.setNameAr(itemCategoryCreationModel.getNameAr());
        itemCategory.setDescription(itemCategoryCreationModel.getDescription());
        itemCategory.setDescriptionAr(itemCategoryCreationModel.getDescriptionAr());
        itemCategory.setParent(parentCategory);
        itemCategoryRepo.save(itemCategory);
        return "Created Successfully";
    }

    public String update(Long id, ItemCategoryCreationModel itemCategoryCreationModel) throws ApiException {
        ItemCategory itemCategory1 = itemCategoryRepo.findById(id).orElse(null);
        if (itemCategory1 == null) {
            throw new ApiException(404, "Item category not found");
        }
        ItemCategory parentCategory = null;
        if (itemCategoryCreationModel.getParentId() != null) {
            parentCategory = itemCategoryRepo.findById(itemCategoryCreationModel.getParentId()).orElse(null);
            if (parentCategory == null) {
                throw new ApiException(404, "Parent category not found");
            }
        }
        itemCategory1.setName(itemCategoryCreationModel.getName());
        itemCategory1.setNameAr(itemCategoryCreationModel.getNameAr());
        itemCategory1.setDescription(itemCategoryCreationModel.getDescription());
        itemCategory1.setDescriptionAr(itemCategoryCreationModel.getDescriptionAr());
        itemCategory1.setParent(parentCategory);
        itemCategoryRepo.save(itemCategory1);
        return ResponseText.UPDATED.text();
    }

    public List<ItemCategory> getCategoriesForItem() {
        return itemCategoryRepo.findAll();

    }
}
