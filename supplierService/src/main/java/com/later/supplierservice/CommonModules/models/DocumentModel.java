package com.later.supplierservice.CommonModules.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentModel {
    private String base64;
    private String type;
    private String name;
}