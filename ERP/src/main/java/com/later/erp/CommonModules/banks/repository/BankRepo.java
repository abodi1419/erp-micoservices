package com.later.erp.CommonModules.banks.repository;

import com.later.erp.CommonModules.banks.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepo extends JpaRepository<Bank, Long> {
}
