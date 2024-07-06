package com.later.erp.CommonModules.industryTypes.repository;

import com.later.erp.CommonModules.industryTypes.entity.IndustryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryTypeRepo extends JpaRepository<IndustryType, Long> {
}
