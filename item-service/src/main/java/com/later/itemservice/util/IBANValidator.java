package com.later.itemservice.util;

import fr.marcwrobel.jbanking.iban.Iban;

import java.util.HashMap;
import java.util.Map;

public class IBANValidator {
    private static final Map<String, Integer> CODE_LENGTHS = new HashMap<>();

    static {
        CODE_LENGTHS.put("AD", 24);
        CODE_LENGTHS.put("AE", 23);
        CODE_LENGTHS.put("AT", 20);
        CODE_LENGTHS.put("AZ", 28);
        CODE_LENGTHS.put("BA", 20);
        CODE_LENGTHS.put("BE", 16);
        CODE_LENGTHS.put("BG", 22);
        CODE_LENGTHS.put("BH", 22);
        CODE_LENGTHS.put("BR", 29);
        CODE_LENGTHS.put("CH", 21);
        CODE_LENGTHS.put("CR", 21);
        CODE_LENGTHS.put("CY", 28);
        CODE_LENGTHS.put("CZ", 24);
        CODE_LENGTHS.put("DE", 22);
        CODE_LENGTHS.put("DK", 18);
        CODE_LENGTHS.put("DO", 28);
        CODE_LENGTHS.put("EE", 20);
        CODE_LENGTHS.put("ES", 24);
        CODE_LENGTHS.put("FI", 18);
        CODE_LENGTHS.put("FO", 18);
        CODE_LENGTHS.put("FR", 27);
        CODE_LENGTHS.put("GB", 22);
        CODE_LENGTHS.put("GI", 23);
        CODE_LENGTHS.put("GL", 18);
        CODE_LENGTHS.put("GR", 27);
        CODE_LENGTHS.put("GT", 28);
        CODE_LENGTHS.put("HR", 21);
        CODE_LENGTHS.put("HU", 28);
        CODE_LENGTHS.put("IE", 22);
        CODE_LENGTHS.put("IL", 23);
        CODE_LENGTHS.put("IS", 26);
        CODE_LENGTHS.put("IT", 27);
        CODE_LENGTHS.put("JO", 30);
        CODE_LENGTHS.put("KW", 30);
        CODE_LENGTHS.put("KZ", 20);
        CODE_LENGTHS.put("LB", 28);
        CODE_LENGTHS.put("LI", 21);
        CODE_LENGTHS.put("LT", 20);
        CODE_LENGTHS.put("LU", 20);
        CODE_LENGTHS.put("LV", 21);
        CODE_LENGTHS.put("MC", 27);
        CODE_LENGTHS.put("MD", 24);
        CODE_LENGTHS.put("ME", 22);
        CODE_LENGTHS.put("MK", 19);
        CODE_LENGTHS.put("MR", 27);
        CODE_LENGTHS.put("MT", 31);
        CODE_LENGTHS.put("MU", 30);
        CODE_LENGTHS.put("NL", 18);
        CODE_LENGTHS.put("NO", 15);
        CODE_LENGTHS.put("PK", 24);
        CODE_LENGTHS.put("PL", 28);
        CODE_LENGTHS.put("PS", 29);
        CODE_LENGTHS.put("PT", 25);
        CODE_LENGTHS.put("QA", 29);
        CODE_LENGTHS.put("RO", 24);
        CODE_LENGTHS.put("RS", 22);
        CODE_LENGTHS.put("SA", 24);
        CODE_LENGTHS.put("SE", 24);
        CODE_LENGTHS.put("SI", 19);
        CODE_LENGTHS.put("SK", 24);
        CODE_LENGTHS.put("SM", 27);
        CODE_LENGTHS.put("TN", 24);
        CODE_LENGTHS.put("TR", 26);
        CODE_LENGTHS.put("AL", 28);
        CODE_LENGTHS.put("BY", 28);
        CODE_LENGTHS.put("CR", 22);
        CODE_LENGTHS.put("EG", 29);
        CODE_LENGTHS.put("GE", 22);
        CODE_LENGTHS.put("IQ", 23);
        CODE_LENGTHS.put("LC", 32);
        CODE_LENGTHS.put("SC", 31);
        CODE_LENGTHS.put("ST", 25);
        CODE_LENGTHS.put("SV", 28);
        CODE_LENGTHS.put("TL", 23);
        CODE_LENGTHS.put("UA", 29);
        CODE_LENGTHS.put("VA", 22);
        CODE_LENGTHS.put("VG", 24);
        CODE_LENGTHS.put("XK", 20);
    }

    public static boolean isValidIBANNumber(String input) {
        return Iban.isValid(input);
    }
}
