package com.github.yurivin.blockjane;

import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.transaction.inoutmodel.InOutTransaction;
import com.github.yurivin.blockjane.wallet.PublicKeyWallet;
import com.github.yurivin.blockjane.wallet.iWallet;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.security.*;

public class TestSignature {

    @Test
    public void testECDSA() {
        Environment env = new Environment();
        //Setup Bouncey castle as a Security Provider
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        //Create the new wallets
        iWallet walletA = new PublicKeyWallet(env);
        iWallet walletB = new PublicKeyWallet(env);
        //Create a test transaction from WalletA to walletB
        InOutTransaction transaction = new InOutTransaction(walletA, walletB.getPublicKey(), new BigDecimal("5"), new Environment());
        //Verify the signature works and verify it from the public key
        Assert.assertTrue(transaction.verifiySignature());
    }
}
