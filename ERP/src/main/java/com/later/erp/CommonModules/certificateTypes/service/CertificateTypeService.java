package com.later.erp.CommonModules.certificateTypes.service;

import com.later.erp.CommonModules.certificateTypes.entity.CertificateType;
import com.later.erp.CommonModules.certificateTypes.repository.CertificateTypeRepo;
import com.later.erp.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateTypeService {
    private final CertificateTypeRepo certificateTypeRepo;

    public List<CertificateType> findAll() {
        return certificateTypeRepo.findAll();
    }

    public CertificateType findById(Long id) throws ApiException {
        CertificateType certificateType = certificateTypeRepo.findById(id).orElse(null);
        if (certificateType == null) {
            throw new ApiException(404, "Certificate type not found");
        }
        return certificateType;
    }

    public List<CertificateType> findAllMandatory() {
        return certificateTypeRepo.getMandatory();
    }

    public CertificateType findByIdOrElseNull(Long id) throws ApiException {
        return certificateTypeRepo.findById(id).orElse(null);
    }
}
