package com.later.erp.App.company.company.repository;

import com.later.erp.App.company.company.entity.CompanyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyHistoryRepo extends JpaRepository<CompanyHistory, Long> {
}
