package com.beta.replyservice;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ReplyMessage {

    private final String message;

    public ReplyMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static String encodeStringtoMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());

            // Convert the byte array to a Hex String
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return "Error encoding the string";
        }
    }

    public static String reverseString(String input) {
        String reversedString = new StringBuilder(input).reverse().toString();
        return reversedString;
    }
}