package com.later.customerservice.App.customer.repository;

import com.later.customerservice.App.customer.entity.LoyaltyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoyaltyProgramRepo extends JpaRepository<LoyaltyProgram, Long> {
    List<LoyaltyProgram> findAllByIdIn(List<Long> ids);
}
