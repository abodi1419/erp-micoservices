package com.later.inventoryservice.App.items.service;

import com.later.inventoryservice.App.items.entity.ItemCategory;
import com.later.inventoryservice.Exception.ApiException;
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

}
