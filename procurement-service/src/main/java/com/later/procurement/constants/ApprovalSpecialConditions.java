package com.later.procurement.constants;

public enum ApprovalSpecialConditions {
    COST_CONTROL("Cost Control Attachment", 1),
    FINANCE("Finance Attachment", 2),
    PROCUREMENT_MANAGER("Procurement Manager", 3),
    GENERATE_VOUCHER("Generate Voucher", 4),
    SELECT_POST_DATE("Select Post Date", 5),
    SELECT_BUYER("Select a buyer", 6),

    ;
    private final String text;
    private final int id;

    private ApprovalSpecialConditions(String text, int id) {
        this.text = text;
        this.id = id;
    }

    public String text() {
        return text;
    }

    public int id() {
        return id;
    }
}
