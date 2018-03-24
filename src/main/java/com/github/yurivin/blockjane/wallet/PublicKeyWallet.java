package com.github.yurivin.blockjane.wallet;

import com.github.yurivin.blockjane.infrastracture.Environment;

import java.math.BigDecimal;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import static com.github.yurivin.blockjane.utils.StringUtils.*;

/**
 * Cryptography used - https://en.wikipedia.org/wiki/Elliptic-curve_cryptography
 */

public class PublicKeyWallet  implements iWallet {

    public PublicKeyWallet(Environment env){
        this.env = env;
        generateKeyPair();
    }

    private PrivateKey privateKey;
    /**
     * Public key acts as an address in this wallet
     */
    private PublicKey publicKey;

    private Environment env;

    private void generateKeyPair() {
        if(Security.getProvider("BC") == null) {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        }
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
            throw new RuntimeException("Error generating keyPair for Wallet",e);
        }
    }

    @Override
    public PublicKey getWalletAddress() {
        return publicKey;
    }
    @Override
    public PublicKey getPublicKey() {
        return publicKey;
    }
    @Override
    public byte[] generateSignature(BigDecimal amount,PublicKey recipient) {
        String data = getStringFromKey(publicKey) + getStringFromKey(recipient) + amount.toString();
        return env.signature.apply(privateKey,data);
    }

    /**
     * This method is used to create wallet instance in blockchain.
     * Every wallet should realize it.
     * @param env
     * @return
     */
    public static iWallet instantiate(Environment env) {
        return new PublicKeyWallet(env);
    }


}
