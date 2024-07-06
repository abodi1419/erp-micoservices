package com.later.companyservice.App.company.employees.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class EmployeeCreationModel {
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String middleName;
    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    @NotBlank
    private String firstNameAr;
    @NotNull
    @NotBlank
    private String middleNameAr;
    @NotNull
    @NotBlank
    private String lastNameAr;
    @NotNull
    @NotBlank
    private String companyNumber;
    @NotNull
    @Positive
    private Integer active;
    @NotNull
    @NotBlank
    private String ssn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfHire;
    @NotNull
    @Positive
    private Long loginUserId;
    private String imagePath;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @Positive
    private Long jobTitle;
    @NotNull
    @Positive
    private Integer level;
}
