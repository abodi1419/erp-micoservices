package com.later.inventoryservice.CommonModules.certificateTypes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class CertificateType {
    private Long id;
    private String name;
    private String nameAr;
    private String description;
    private String descriptionAr;
    private Boolean required;
    private Boolean expires = false;
}
