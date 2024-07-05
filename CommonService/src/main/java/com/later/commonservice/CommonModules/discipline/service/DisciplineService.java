package com.later.commonservice.CommonModules.discipline.service;

import com.later.commonservice.CommonModules.discipline.entity.Discipline;
import com.later.commonservice.CommonModules.discipline.repository.DisciplineRepo;
import com.later.commonservice.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplineService {
    private final DisciplineRepo disciplineRepo;

    public List<Discipline> findAll() {
        return disciplineRepo.findAll();
    }

    public Discipline findById(Long id) throws ApiException {
        Discipline discipline = disciplineRepo.findById(id).orElse(null);
        if (discipline == null) {
            throw new ApiException(404, "Discipline not found");
        }
        return discipline;
    }

    public Discipline findByIdOrElseNull(Long id) {
        return disciplineRepo.findById(id).orElse(null);
    }
}
