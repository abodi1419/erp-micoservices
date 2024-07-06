package com.later.erp.App.items.repository;

import com.later.erp.App.items.entity.MeasureUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasureUnitRepo extends JpaRepository<MeasureUnit, Long> {
    List<MeasureUnit> findAllByService(Boolean service);
}
