package com.github.yurivin.blockjane.wallet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.transaction.iTransaction;

import java.math.BigDecimal;
import java.security.PublicKey;

public interface iWallet {

    PublicKey getWalletAddress();
    PublicKey getPublicKey();
    byte[] generateSignature(BigDecimal amount, PublicKey recipient);
    /**
     *  Returns balance and stores the UTXO's owned by this wallet in this.UTXOs
     */
    BigDecimal getBalance();

    iTransaction sendFunds(PublicKey recipient, BigDecimal value ) throws JsonProcessingException;
}
