package com.github.yurivin.blockjane.transaction;

import java.security.PrivateKey;

public interface iTransaction {

    /**
     *     Verifies the data we signed hasnt been tampered with
     */
    boolean verifiySignature();
}
