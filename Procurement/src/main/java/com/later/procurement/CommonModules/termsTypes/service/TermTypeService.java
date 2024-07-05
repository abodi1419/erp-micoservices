package com.later.procurement.CommonModules.termsTypes.service;


import com.later.procurement.CommonModules.termsTypes.entity.TermType;
import com.later.procurement.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermTypeService {

    public List<TermType> findAll() {
        return List.of();
    }

    public TermType findById(Long id) throws ApiException {
        return null;
    }

    public TermType findByIdOrElseNull(Long id) throws ApiException {
        return null;
    }
}
