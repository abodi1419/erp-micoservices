package com.later.commonservice.CommonModules.termsTypes.repository;

import com.later.commonservice.CommonModules.termsTypes.entity.TermType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermTypeRepo extends JpaRepository<TermType, Long> {
}
