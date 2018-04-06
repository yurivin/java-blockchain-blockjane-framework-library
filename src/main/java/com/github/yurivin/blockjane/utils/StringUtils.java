package com.github.yurivin.blockjane.utils;

import java.security.Key;
import java.util.Base64;

public class StringUtils {

    public static String getStringFromKey(Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}
