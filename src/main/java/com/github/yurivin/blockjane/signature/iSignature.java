package com.github.yurivin.blockjane.signature;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface iSignature {

    byte[] apply(PrivateKey privateKey, String input);

    boolean verify(PublicKey publicKey, String data, byte[] signature);
}
