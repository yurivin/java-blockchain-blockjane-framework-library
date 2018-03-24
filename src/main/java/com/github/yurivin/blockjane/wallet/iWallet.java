package com.github.yurivin.blockjane.wallet;

import com.github.yurivin.blockjane.infrastracture.Environment;

import java.math.BigDecimal;
import java.security.PublicKey;

public interface iWallet {

    PublicKey getWalletAddress();
    PublicKey getPublicKey();
    byte[] generateSignature(BigDecimal amount, PublicKey recipient);

}
