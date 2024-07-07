package com.later.itemservice.CommonModules.deliveryLocation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter

public class DeliveryLocation {
    private Long id;
    private String name;
    private String nameAr;
    private String description;
    private String descriptionAr;
    private String address;
    private String addressAr;
    private String googleMapAddress;

}
