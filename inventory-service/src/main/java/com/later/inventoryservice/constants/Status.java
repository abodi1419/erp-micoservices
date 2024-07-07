package com.later.inventoryservice.constants;

/*
    Draft code is always 0
    Approve codes are between 50 - 59
    Reject codes are between 60 - 69
    Cancellation codes are between 70 - 79
    Revision codes are between 80 - 89
 */
public enum Status {

    DRAFT((short) 0, "Draft", "مسودَة"),
    APPROVED((short) 50, "Approved", "تم الإعتماد"),
    REJECTED((short) 60, "Rejected", "تم الرفض"),
    REJECTED_FOR_MODIFY((short) 61, "Rejected For Modify", "تم الرفض يتطلب التعديل"),
    REJECTED_FOR_RETURN((short) 62, "Rejected For Return", "تم الرفض للإرجاع"),
    CANCELLED((short) 70, "Cancelled", "تم الإلغاء"),
    CANCELLATION_ASKED((short) 71, "Cancellation Asked", "تم طلب الإلغاء"),
    CANCELLATION_ASKED_ِAPPROVED((short) 72, "Cancellation Approved", "تم إعتماد طلب الإلغاء"),
    REQUEST_FOR_CANCELLATION((short) 75, "Cancellation Asked", "تم طلب الإلغاء"),
    REVISED((short) 80, "Revised", "تمت المراجعة"),
    ;
    private final Short code;
    private final String desc;
    private final String descAr;

    private Status(Short code, String desc, String descAr) {
        this.code = code;
        this.desc = desc;
        this.descAr = descAr;
    }

    public Short code() {
        return code;
    }

    public String desc() {
        return desc;
    }

    public String descAr() {
        return descAr;
    }

}
