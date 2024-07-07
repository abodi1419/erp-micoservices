package com.later.customerservice.App.customer.repository;

import com.later.customerservice.App.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    @Query("select coalesce(max(c.serial), 0)+1 from Customer c")
    Integer getCurrentSerial();
}
