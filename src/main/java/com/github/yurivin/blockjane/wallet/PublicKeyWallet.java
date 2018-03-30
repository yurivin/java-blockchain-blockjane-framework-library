package com.github.yurivin.blockjane.wallet;

import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.transaction.inoutmodel.InOutTransaction;
import com.github.yurivin.blockjane.transaction.inoutmodel.TransactionInput;
import com.github.yurivin.blockjane.transaction.iTransaction;
import com.github.yurivin.blockjane.transaction.iTransactionInput;
import com.github.yurivin.blockjane.transaction.iPendingTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.yurivin.blockjane.utils.StringUtils.*;

/**
 * Cryptography used - https://en.wikipedia.org/wiki/Elliptic-curve_cryptography
 */

public class PublicKeyWallet  implements iWallet {

    private final Logger log = LoggerFactory.getLogger(getClass());

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
    /**
     * Only UTXOs owned by this wallet.
     */
    private final Map<String, iPendingTransaction> UTXOs = new HashMap<>();


    /**
     *     Generates and returns a new transaction from this wallet.
     */
    public iTransaction sendFunds(PublicKey recipient, BigDecimal value ) {
        if(getBalance().compareTo(value) == -1) { //gather balance and check funds.
            System.out.println("#Not Enough funds to send transaction. Transaction Discarded.");
            return null;
        }
        //create array list of inputs
        List<iTransactionInput> inputs = new ArrayList<>();

        BigDecimal total = new BigDecimal("0");
        for (Map.Entry<String, iPendingTransaction> item: UTXOs.entrySet()){
            iPendingTransaction UTXO = item.getValue();
            total.add(UTXO.getAmount());
            inputs.add(new TransactionInput(UTXO.getId()));
            if(total.compareTo(value) == 1) break;
        }

        iTransaction newTransaction = new InOutTransaction(this, recipient , value, inputs, env);

        for(iTransactionInput input: inputs){
            UTXOs.remove(input.getTransactionOutputId());
        }
        return newTransaction;
    }
    /**
     *  Returns balance and stores the UTXO's owned by this wallet in this.UTXOs
     */
    @Override
    public BigDecimal getBalance() {
        BigDecimal total = new BigDecimal("0");
        for (Map.Entry<String, iPendingTransaction> item: env.transactionEnv.getPending().entrySet()){
            iPendingTransaction UTXO = item.getValue();
            if(UTXO.isAssignedTo(publicKey)) { //if output belongs to me ( if coins belong to me )
                UTXOs.put(UTXO.getId(),UTXO); //add it to our list of unspent transactions.
                total.add(UTXO.getAmount());
            }
        }
        return total;
    }

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
