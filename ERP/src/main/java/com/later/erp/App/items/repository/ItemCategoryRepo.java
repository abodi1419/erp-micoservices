package com.later.erp.App.items.repository;

import com.later.erp.App.items.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepo extends JpaRepository<ItemCategory, Long> {
}
