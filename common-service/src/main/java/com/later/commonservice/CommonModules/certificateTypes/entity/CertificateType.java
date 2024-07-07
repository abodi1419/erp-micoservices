package com.later.commonservice.CommonModules.certificateTypes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "common_certificate_types")
public class CertificateType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nameAr;
    private String description;
    private String descriptionAr;
    @Column(columnDefinition = "bit default 0")
    private Boolean required;
    @Column(columnDefinition = "bit default 0")
    private Boolean expires = false;
}
