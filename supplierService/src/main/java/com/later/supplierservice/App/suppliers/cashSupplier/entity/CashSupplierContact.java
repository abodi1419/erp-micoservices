package com.later.supplierservice.App.suppliers.cashSupplier.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.supplierservice.interfaces.commonEntity.CityInterface;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "sp_cash_supplier_contacts", uniqueConstraints = {
        @UniqueConstraint(name = "name_email_phone", columnNames = {"contactName", "contactEmail", "contactPhone"})
})
public class CashSupplierContact implements CityInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private CashSupplier supplier;
    private String contactName;
    private String contactNameAr;
    private String contactJobTitle;
    private String contactJobTitleAr;
    @Email(message = "Must be in the format example@example.com")
    private String contactEmail;
    @Column(length = 20)
    @Size(max = 20)
    @Pattern(regexp = "\\+?\\p{Digit}+$", message = "Must contain a phone number")
    private String contactPhone;
    private Long cityId;
    private String cityName;
    private String cityNameAr;
    @Column(columnDefinition = "tinyint")
    private Short preferred;
}
