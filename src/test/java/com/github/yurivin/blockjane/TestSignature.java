package com.github.yurivin.blockjane;

import com.github.yurivin.blockjane.identity.PublicKeyIdentity;
import com.github.yurivin.blockjane.identity.iIdentity;
import com.github.yurivin.blockjane.infrastracture.Environment;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.security.Security;

import static com.github.yurivin.blockjane.utils.StringUtils.getStringFromKey;

public class TestSignature {

    @Test
    public void testECDSA() {
        Environment env = new Environment();
        //Setup Bouncey castle as a Security Provider
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        //Create the new identities
        iIdentity walletA = new PublicKeyIdentity(env);
        iIdentity walletB = new PublicKeyIdentity(env);
        String data = "15";
        byte[] signature = walletA.generateSignature(data, walletB.getPublicKey());
        //Verify the signature works and verify it from the public key
        Assert.assertTrue(verifiySignature(signature, data, walletA.getPublicKey(), walletB.getPublicKey(), env));
    }

    public boolean verifiySignature(byte[] signature, String data, PublicKey sender, PublicKey recipient,Environment env) {
        data = getStringFromKey(sender) + getStringFromKey(recipient) + data;
        return env.signature.verify(sender, data, signature);
    }
}
