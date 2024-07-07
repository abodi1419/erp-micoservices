package com.later.customerservice.App.customer.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class CustomerCreationModel {

    @NotNull
    @NotBlank
    private String nameAr;
    private String name;
    @NotNull
    @NotBlank
    private String phone;
    private String email;
    private String address;
    private List<Long> loyaltyPrograms;


}
