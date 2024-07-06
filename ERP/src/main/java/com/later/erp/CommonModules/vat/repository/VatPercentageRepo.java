package com.later.erp.CommonModules.vat.repository;

import com.later.erp.CommonModules.vat.entity.VatPercentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VatPercentageRepo extends JpaRepository<VatPercentage, Long> {
    @Query("select v from VatPercentage v where v.active=true ")
    List<VatPercentage> findAllActive();
}
