package com.later.inventoryservice.App.items.service;


import com.later.inventoryservice.App.items.entity.Item;

import com.later.inventoryservice.CommonModules.discipline.service.DisciplineService;
import com.later.inventoryservice.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final DisciplineService disciplineService;


    public List<Item> findAll() {
        return List.of();

    }

    public List<Item> findAllById(List<Long> ids) {
        return List.of();
    }

    public List<Item> findAllByIdUnderDiscipline(List<Long> ids, Long disciplineId) {
        return List.of();
    }

    public Item findById(Long id) throws ApiException {
       return null;
    }
    public List<Item> findAllUnderDiscipline(Long disciplineId) {
        return List.of();
    }
}
