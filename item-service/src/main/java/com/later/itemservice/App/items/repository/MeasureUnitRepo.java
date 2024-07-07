package com.later.itemservice.App.items.repository;

import com.later.itemservice.App.items.entity.MeasureUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasureUnitRepo extends JpaRepository<MeasureUnit, Long> {
    List<MeasureUnit> findAllByService(Boolean service);
}
