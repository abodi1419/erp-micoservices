package com.later.erp.CommonModules.currencies.repository;

import com.later.erp.CommonModules.currencies.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency, Long> {
}