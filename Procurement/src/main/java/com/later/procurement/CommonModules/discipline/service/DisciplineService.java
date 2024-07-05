package com.later.procurement.CommonModules.discipline.service;


import com.later.procurement.CommonModules.discipline.entity.Discipline;
import com.later.procurement.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplineService {


    public List<Discipline> findAll() {
        return List.of();
    }

    public Discipline findById(Long id) throws ApiException {
        return null;
    }

    public Discipline findByIdOrElseNull(Long id) throws ApiException {
            return null;
    }
}
