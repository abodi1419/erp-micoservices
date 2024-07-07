package com.later.customerservice.App.customer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "customer_loyalty_programs")
public class LoyaltyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JsonIgnore
    private List<Customer> customer;
    @OneToMany(mappedBy = "loyaltyProgram")
    private List<LoyaltyProgramDetails> loyaltyProgramDetails;
    private Boolean active;
    private String name;
    private String nameAr;
    private String description;
    private String descriptionAr;


}
