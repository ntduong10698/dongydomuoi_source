package com.bksoftwarevn.adminthuocdongy.userservice.utils;

import lombok.extern.log4j.Log4j2;

import java.security.MessageDigest;

@Log4j2
public class EncodeUtil {

    public static String getSHA256(String password) {
        String rs = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            rs = bytesToHex(md.digest());
        } catch (Exception ex) {
            log.error("Error get-sha256: {}", ex.getMessage());
        }
        System.out.println("rs:  "+rs);
        return rs;
    }


    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte byt : bytes) {
            result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }


}
