package com.later.inventoryservice.constants;

public enum RequestFilters {
    WAITING_MY_ACTION("waiting_my_action"),
    ACTIVE("active"),
    CLOSED("closed"),
    MY_REQUESTS("my_requests"),
    ;
    private final String filter;

    RequestFilters(String filter) {
        this.filter = filter;
    }

    public String filter() {
        return filter;
    }
}
