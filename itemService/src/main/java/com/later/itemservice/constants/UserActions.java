package com.later.itemservice.constants;

public enum UserActions {
    CREATE("Create"),
    UPDATE("Update"),
    DELETE("Delete"),
    APPROVE("Approve"),
    REJECT("Reject"),
    CANCEL("Cancel"),
    REVISED("Revised"),
    ;


    private final String action;

    UserActions(String action) {
        this.action = action;
    }

    public String action() {
        return action;
    }
}
