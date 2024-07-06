package com.later.erp.App.superAdmin.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccessCreationModel {
    @NotNull
    private Long loginUserId;
    private Long routeId;
    @NotNull
    @NotEmpty
    private List<Long> accesses;
}
