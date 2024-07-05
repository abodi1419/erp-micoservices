package com.later.procurement.App.procurement.PurchaseOrder.service;

public class PurchaseOrderConstants {

    public enum Type {

        PURCHASE((short) 1, "Purchase"),
        SERVICE((short) 2, "Service"),
        ;
        private final Short id;
        private final String type;

        Type(Short id, String type) {
            this.id = id;
            this.type = type;
        }

        public Short id() {
            return id;
        }

        public String type() {
            return type;
        }

        public static Boolean checkById(Short id) {
            for (Type type : Type.values()) {
                if (type.id().equals(id)) {
                    return true;
                }
            }
            return false;
        }
    }
}
