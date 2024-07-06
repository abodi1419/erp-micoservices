package com.later.erp.App.customer.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "customer_customers_history")
public class CustomerHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long refId;
    private String refCode;
    private Integer serial;
    private String name;
    private String nameAr;
    private String phone;
    private String email;
    private String address;
    private Long actionBy;
    private String action;
    private LocalDateTime actionAt = LocalDateTime.now();
}
