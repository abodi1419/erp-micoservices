package com.later.commonservice.CommonModules.businessTypes.repository;

import com.later.commonservice.CommonModules.businessTypes.entity.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessTypeRepo extends JpaRepository<BusinessType, Long> {
}
