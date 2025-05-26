package com.smartjob.creacionusuarios.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public final class Md5Generator {
    // Helper method to keep the main call simple
    public static String generateMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes); // Or convert to hex
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
}
