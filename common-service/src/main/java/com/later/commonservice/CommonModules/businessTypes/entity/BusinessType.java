package com.later.commonservice.CommonModules.businessTypes.entity;

import com.later.commonservice.CommonModules.certificateTypes.entity.CertificateType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "common_business_types")
public class BusinessType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeOfBusiness;
    private String typeOfBusinessAr;
    private Boolean requiredCertificate;
    @ManyToOne
    private CertificateType certificateType;
}
