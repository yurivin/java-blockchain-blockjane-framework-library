package com.github.yurivin.blockjane.hash;

import com.github.yurivin.blockjane.hash.iAlgo;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 implements iAlgo {
    @Override
    public String apply(String input) {
        try {
            return genHash(input);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    private String genHash(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        //Applies sha256 to our input,
        byte[] hash = digest.digest(input.getBytes("UTF-8"));
        StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
