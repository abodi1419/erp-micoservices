package com.later.erp.App.inventory.stocks.stock.repository;

import com.later.erp.App.inventory.stocks.stock.entity.StockHist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockHistRepo extends JpaRepository<StockHist, Integer> {
}