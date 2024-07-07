package com.later.commonservice.constants;

public enum ResponseText {
    CREATED("Created successfully"),
    UPDATED("Updated successfully"),
    APPROVED("Approved successfully"),
    REJECTED("Rejected successfully"),
    DELETED("Deleted successfully"),
    RETRIEVED("Retrieved successfully"),
    SUBMITTED("Submitted successfully");
    private String text;

    private ResponseText(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }

}
