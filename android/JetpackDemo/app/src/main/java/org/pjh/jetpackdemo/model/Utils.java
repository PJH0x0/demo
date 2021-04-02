package org.pjh.jetpackdemo.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static final String PASSWORD_MASK = "(sw_login_service)";

    public static String MD5(String key) {
        return MD5(key.getBytes());
    }

    public static String MD5(byte[] bytes) {
        String cacheKey;
        try{
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(bytes);
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(bytes.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static String getMaskPassword(String password) {
        return MD5(password + PASSWORD_MASK);
    }

}
