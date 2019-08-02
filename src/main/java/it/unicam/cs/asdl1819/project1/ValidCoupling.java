package it.unicam.cs.asdl1819.project1;

import java.util.Arrays;

class ValidCoupling {
    private static String[] validCouples = new String[]{"GC", "CG", "AU", "UA", "GU", "UG"};

    static boolean isValidCoupling(String couple) {
        return Arrays.asList(validCouples).contains(couple);
    }
}
