package com.later.commonservice.CommonModules.industryTypes.repository;

import com.later.commonservice.CommonModules.industryTypes.entity.IndustryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryTypeRepo extends JpaRepository<IndustryType, Long> {
}
