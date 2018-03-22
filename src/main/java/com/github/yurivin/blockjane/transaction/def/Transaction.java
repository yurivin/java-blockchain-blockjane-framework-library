package com.github.yurivin.blockjane.transaction.def;

import com.github.yurivin.blockjane.infrastracture.Environment;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * https://en.bitcoin.it/wiki/Transaction#Explanation
 */
public class Transaction {

    private Environment env;
    /**
     * Transaction hash is unique identifier of transaction
     */
    public String hash;
    public PublicKey sender;
    public PublicKey recipient;
    public BigDecimal value;
    /**
     * Signature combined with the public key, proves the transaction was created by the real
     * owner of the address in question. Signature allows only the owner to spend their coins.
     * Signature prevents others from tampering with their submitted transaction before
     * a new block is mined (at the point of entry).
     */
    public byte[] signature;

    public List<TransactionInput> inputs;
    public List<TransactionOutput> outputs = new ArrayList<>();

    // Constructor:
    public Transaction(PublicKey from, PublicKey to, BigDecimal value, ArrayList<TransactionInput> inputs, Environment env) {
        this.sender = from;
        this.recipient = to;
        this.value = value;
        this.inputs = inputs;
        this.env = env;
    }

    private String calulateHash() {
        Long date = System.currentTimeMillis();
        if(env.lastTransactionTimestamp.get() == new Date().getTime()) {
            env.transactionCount.incrementAndGet();   //increase the sequence to avoid 2 identical transactions having the same hash
        } else {
            env.transactionCount.set(0);
            env.lastTransactionTimestamp.set(date);
        }
        return env.hashAlgo.apply(
                TransactionsStringUtils.getStringFromKey(sender) +
                        TransactionsStringUtils.getStringFromKey(recipient) +
                        value.toString() + transactionUnifier()
        );
    }

    /**
     * This method should return value to make transactoin hash unique in guarantied way
     * @return
     */
    @NotNull
    private String transactionUnifier() {
        return String.valueOf(env.lastTransactionTimestamp.get())
                + env.transactionCount + env.nodeStartupTime + env.nodeStartupUuid.toString();
    }

}
