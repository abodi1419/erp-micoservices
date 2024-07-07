package com.later.commonservice.CommonModules.banks.repository;

import com.later.commonservice.CommonModules.banks.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepo extends JpaRepository<Bank, Long> {
}
