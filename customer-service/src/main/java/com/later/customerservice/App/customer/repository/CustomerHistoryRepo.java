package com.later.customerservice.App.customer.repository;

import com.later.customerservice.App.customer.entity.CustomerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerHistoryRepo extends JpaRepository<CustomerHistory, Long> {
}
