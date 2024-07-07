package com.later.customerservice.App.customer.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "customer_customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String refCode;
    @Column(unique = true)
    private Integer serial;
    private String name;
    @NotNull
    @NotBlank
    private String nameAr;
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String phone;
    private String email;
    private String address;
    @ManyToMany()
    @JoinTable(name = "customer_loyalty_program_relation")
    private List<LoyaltyProgram> loyaltyPrograms;
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<LoyaltyProgramDetails> loyaltyProgramDetails;

}
