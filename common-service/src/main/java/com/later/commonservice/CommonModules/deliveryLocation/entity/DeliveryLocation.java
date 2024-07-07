package com.later.commonservice.CommonModules.deliveryLocation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "common_delivery_locations")
public class DeliveryLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nameAr;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String descriptionAr;
    @Column(columnDefinition = "text")
    private String address;
    @Column(columnDefinition = "text")
    private String addressAr;
    private String googleMapAddress;

}
