package com.github.yurivin.blockjane.identity;

import java.security.PublicKey;

public interface iIdentity {

    PublicKey getIdentityAddress();
    PublicKey getPublicKey();
    byte[] generateSignature(String data, PublicKey recipient);

}
