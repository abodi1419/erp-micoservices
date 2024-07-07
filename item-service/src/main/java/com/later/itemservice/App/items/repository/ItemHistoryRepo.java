package com.later.itemservice.App.items.repository;

import com.later.itemservice.App.items.entity.ItemHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemHistoryRepo extends JpaRepository<ItemHistory, Long> {
}
