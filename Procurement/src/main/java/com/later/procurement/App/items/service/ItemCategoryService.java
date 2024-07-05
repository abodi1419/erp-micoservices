package com.later.procurement.App.items.service;


import com.later.procurement.App.items.entity.ItemCategory;
import com.later.procurement.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemCategoryService {


    public List<ItemCategory> findAll() {
        return List.of();
    }

    public ItemCategory findById(Long id) throws ApiException {
        return null;
    }

    public List<ItemCategory> getCategoriesForItem() {
        return List.of();

    }
}
