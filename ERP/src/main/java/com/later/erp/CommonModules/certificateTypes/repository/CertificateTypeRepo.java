package com.later.erp.CommonModules.certificateTypes.repository;

import com.later.erp.CommonModules.certificateTypes.entity.CertificateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateTypeRepo extends JpaRepository<CertificateType, Long> {
    @Query("select c from CertificateType c where c.required=true")
    List<CertificateType> getMandatory();
}
