package com.bksoftwarevn.adminthuocdongy.uploadservice.util;

public class Company {

    public static String getCompanyName(int id) {
        switch (id) {
            case 1:
                return "nataliepmu";
            case 2:
                return "avhh";
            case 3:
                return "dong-y";
            default:
                return "other";
        }
    }

    public static String getCompanyConfig(int id) {
        return getCompanyName(id) + "-config";
    }
}
