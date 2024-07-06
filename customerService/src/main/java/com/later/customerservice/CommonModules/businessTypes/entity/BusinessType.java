package com.later.customerservice.CommonModules.businessTypes.entity;

import com.later.customerservice.CommonModules.certificateTypes.entity.CertificateType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class BusinessType {
    private Long id;
    private String typeOfBusiness;
    private String typeOfBusinessAr;
    private Boolean requiredCertificate;
    private CertificateType certificateType;
}
