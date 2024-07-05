package com.later.procurement.CommonModules.certificateTypes.service;


import com.later.procurement.CommonModules.certificateTypes.entity.CertificateType;
import com.later.procurement.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateTypeService {


    public List<CertificateType> findAll() {
        return List.of();
    }

    public CertificateType findById(Long id) throws ApiException {
        return null;
    }

    public List<CertificateType> findAllMandatory() {
        return List.of();
    }

    public CertificateType findByIdOrElseNull(Long id) throws ApiException {
        return null;
    }
}
