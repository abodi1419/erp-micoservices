package com.later.commonservice.CommonModules.discipline.repository;

import com.later.commonservice.CommonModules.discipline.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepo extends JpaRepository<Discipline, Long> {
}
