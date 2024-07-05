package com.later.procurement.CommonModules.company.division.service;

import com.later.procurement.CommonModules.company.division.entity.Division;
import com.later.procurement.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DivisionService {

    private List<Division> findAll() {
        return List.of();
    }

    public List<Division> findAllUnderDepartment(Long departmentId) {
        return List.of();
    }

    public Division findById(Long id) throws ApiException {
        return null;
    }

    public Division findByIdUnderDepartment(Long id, Long departmentId) throws ApiException {
        return null;
    }

}
