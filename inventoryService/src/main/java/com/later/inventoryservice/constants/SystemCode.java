package com.later.inventoryservice.constants;

import java.util.Arrays;

public enum SystemCode {
    ITEM("Item", "I", "item"),
    SUPPLIER("Supplier", "SP", "supplier"),
    WAREHOUSE("Warehouse", "WAR", "WAR"),
    STOCK("Stock", "ST", "ST"),
    PURCHASE_REQUEST("Purchase Request", "PR", "proc"),
    PURCHASE_ORDER("Purchase Order", "PO", "proc"),
    ;
    private final String systemName;
    private final String systemCode;
    private final String moduleGroup;

    private SystemCode(String systemName, String systemCode, String moduleGroup) {
        this.systemName = systemName;
        this.systemCode = systemCode;
        this.moduleGroup = moduleGroup;
    }

    public String systemCode() {
        return systemCode;
    }

    public String systemName() {
        return systemName;
    }

    public String moduleGroup() {
        return moduleGroup;
    }

    public static SystemCode getBySystemCode(String systemCode) {
        return Arrays.asList(SystemCode.values())
                .stream().filter(s -> s.systemCode.equals(systemCode))
                .findFirst().orElse(null);
    }
}
