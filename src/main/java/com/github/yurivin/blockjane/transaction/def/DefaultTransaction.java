package com.github.yurivin.blockjane.transaction.def;

import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.transaction.iTransaction;
import com.github.yurivin.blockjane.wallet.iWallet;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.github.yurivin.blockjane.utils.StringUtils.getStringFromKey;

/**
 * https://en.bitcoin.it/wiki/Transaction#Explanation
 */
public class DefaultTransaction implements iTransaction {

    private Environment env;
    /**
     * Transaction hash is unique identifier of transaction
     */
    public final String hash;
    public final PublicKey sender;
    public final PublicKey recipient;
    public final BigDecimal value;
    /**
     * Signature combined with the public key, proves the transaction was created by the real
     * owner of the address in question. Signature allows only the owner to spend their coins.
     * Signature prevents others from tampering with their submitted transaction before
     * a new block is mined (at the point of entry).
     */
    public final byte[] signature;

    public List<TransactionInput> inputs;
    public List<TransactionOutput> outputs = new ArrayList<>();

    // Constructor:
    public DefaultTransaction(iWallet from, PublicKey to, BigDecimal amount, ArrayList<TransactionInput> inputs, Environment env) {
        this.sender = from.getPublicKey();
        this.recipient = to;
        this.value = amount;
        this.inputs = inputs;
        this.env = env;
        this.hash = calculateHash();
        this.signature = from.generateSignature(amount, to);
        verifiySignature();
    }

    private String calculateHash() {
        Long date = System.currentTimeMillis();
        if (env.lastTransactionTimestamp.get() == new Date().getTime()) {
            env.transactionCount.incrementAndGet();   //increase the sequence to avoid 2 identical transactions having the same hash
        } else {
            env.transactionCount.set(0);
            env.lastTransactionTimestamp.set(date);
        }
        return env.hashAlgo.apply(
                getStringFromKey(sender) +
                        getStringFromKey(recipient) +
                        value.toString() + transactionUnifier()
        );
    }

    /**
     * This method should return value to make transaction hash unique in guarantied way
     *
     * @return
     */
    @NotNull
    private String transactionUnifier() {
        return String.valueOf(env.lastTransactionTimestamp.get())
                + env.transactionCount + env.nodeStartupTime + env.nodeStartupUuid.toString();
    }

    //Verifies the data we signed hasn't been tampered with
    @Override
    public boolean verifiySignature() {
        String data = getStringFromKey(sender) + getStringFromKey(recipient) + value.toString();
        return env.signature.verify(sender, data, signature);
    }

}
