package com.later.customerservice.util;

public class RefCode {
    public static String generate(String systemCodeOrPrefix, Integer serial) {
        return String.format(systemCodeOrPrefix + "-%07d", serial);
    }

    public static String generateTemp(String systemCodeOrPrefix, Integer serial) {
        return String.format("TMP-" + systemCodeOrPrefix + "-%07d", serial);
    }

}
