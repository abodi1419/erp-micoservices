package com.later.commonservice.CommonModules.addresses.repository;

import com.later.commonservice.CommonModules.addresses.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {
}
