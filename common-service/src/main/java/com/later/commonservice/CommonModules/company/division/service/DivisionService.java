package com.later.commonservice.CommonModules.company.division.service;

import com.later.commonservice.CommonModules.company.division.entity.Division;
import com.later.commonservice.CommonModules.company.division.repository.DivisionRepo;
import com.later.commonservice.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DivisionService {
    private final DivisionRepo divisionRepo;

    public List<Division> findAll() {
        return divisionRepo.findAll();
    }

    public List<Division> findAllUnderDepartment(Long departmentId) {
        return divisionRepo.findAllByDepartment_Id(departmentId);
    }

    public Division findById(Long id) throws ApiException {
        Division division = divisionRepo.findById(id).orElse(null);
        if (division == null) {
            throw new ApiException(404, "Division not found");
        }
        return division;
    }

    public Division findByIdUnderDepartment(Long id, Long departmentId) {
        Division division = divisionRepo.findById(id).orElse(null);
        if (division == null || division.getDepartment() == null || !division.getDepartment().getId().equals(departmentId)) {
            throw null;
        }
        return division;
    }

    public Division findByIdOrElseNull(Long id) {
        return divisionRepo.findById(id).orElse(null);
    }
}
