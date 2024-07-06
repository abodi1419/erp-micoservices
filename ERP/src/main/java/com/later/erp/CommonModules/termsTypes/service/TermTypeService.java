package com.later.erp.CommonModules.termsTypes.service;

import com.later.erp.CommonModules.termsTypes.entity.TermType;
import com.later.erp.CommonModules.termsTypes.repository.TermTypeRepo;
import com.later.erp.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermTypeService {
    private final TermTypeRepo termTypeRepo;

    public List<TermType> findAll() {
        return termTypeRepo.findAll();
    }

    public TermType findById(Long id) throws ApiException {
        TermType termType = termTypeRepo.findById(id).orElse(null);
        if (termType == null) {
            throw new ApiException(404, "Term type not found");
        }
        return termType;
    }

    public TermType findByIdOrElseNull(Long id) throws ApiException {
        return termTypeRepo.findById(id).orElse(null);
    }
}
