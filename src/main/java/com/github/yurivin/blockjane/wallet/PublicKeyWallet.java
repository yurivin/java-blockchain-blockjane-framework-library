package com.github.yurivin.blockjane.wallet;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

/**
 * Cryptography used - https://en.wikipedia.org/wiki/Elliptic-curve_cryptography
 */
public class PublicKeyWallet implements iWallet {

    public PublicKeyWallet(){
        generateKeyPair();
    }

    public PrivateKey privateKey;
    /**
     * Public key acts as an address in this wallet
     */
    public PublicKey publicKey;

    @Override
    public void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            // Initialize the key generator and generate a KeyPair
            keyGen.initialize(ecSpec, random);   //256 bytes provides an acceptable security level
            KeyPair keyPair = keyGen.generateKeyPair();
            // Set the public and private keys from the keyPair
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
