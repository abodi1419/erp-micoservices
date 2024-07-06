package com.later.erp.App.superAdmin.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RouteCreationModel {
    @NotNull
    private String moduleCode;
    @NotNull
    private String moduleName;
    @NotNull
    private String route;
    private Boolean active;
    private Boolean admin;
    @NotNull
    private String moduleSuffix;
    private Boolean requiresAccess;
    private Boolean publicAccess;
    @Valid
    private List<AccessCreationModel> accessCreationModelList;
}
