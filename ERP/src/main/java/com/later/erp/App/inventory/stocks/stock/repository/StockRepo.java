package com.later.erp.App.inventory.stocks.stock.repository;

import com.later.erp.App.inventory.stocks.stock.entity.Stock;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Registered
public interface StockRepo extends JpaRepository<Stock, Long> {
    @Query("select coalesce(max(x.serial), 0)+1 from Stock x")
    Integer getCurrentSerial();
}
