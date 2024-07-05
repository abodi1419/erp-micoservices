package com.later.inventoryservice.App.inventory.stocks.commentService.repository;

import com.later.inventoryservice.App.inventory.stocks.commentService.entity.StockComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockCommentRepo extends JpaRepository<StockComment, Long> {
}
