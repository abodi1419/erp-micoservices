package com.later.commonservice.CommonModules.company.costCenter.repository;

import com.later.commonservice.CommonModules.company.costCenter.entity.CostCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostCenterRepo extends JpaRepository<CostCenter, Long> {
}
