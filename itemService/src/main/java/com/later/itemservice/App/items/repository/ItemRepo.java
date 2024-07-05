package com.later.itemservice.App.items.repository;

import com.later.itemservice.App.items.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
    @Query("select coalesce(max(i.serial), 0)+1 from Item i")
    Integer getCurrentSerial();

    @Query("select i from Item i where i.id in :ids and i.disciplineId=:disciplineId")
    List<Item> findAllByIdAndDiscipline_Id(List<Long> ids, Long disciplineId);

    @Query("select i from Item i where i.disciplineId=:disciplineId")
    List<Item> findAllUnderDiscipline(Long disciplineId);
}
