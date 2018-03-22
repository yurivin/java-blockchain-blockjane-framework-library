package com.github.yurivin.blockjane.transaction.def;

import java.security.Key;
import java.util.Base64;

public class TransactionsStringUtils {

    public static String getStringFromKey(Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}
