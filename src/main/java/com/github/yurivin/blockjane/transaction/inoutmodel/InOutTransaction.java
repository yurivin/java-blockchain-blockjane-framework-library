package com.github.yurivin.blockjane.transaction.inoutmodel;

import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.transaction.iPendingTransaction;
import com.github.yurivin.blockjane.transaction.iTransaction;
import com.github.yurivin.blockjane.transaction.iTransactionInput;
import com.github.yurivin.blockjane.wallet.iWallet;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.github.yurivin.blockjane.utils.StringUtils.getStringFromKey;

/**
 * https://en.bitcoin.it/wiki/Transaction#Explanation
 */
public class InOutTransaction implements iTransaction {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private Environment env;
    /**
     * Transaction transactionId is unique identifier of transaction
     */
    private final String transactionId;
    private final PublicKey sender;
    private final PublicKey recipient;
    private final BigDecimal amount;
    /**
     * Signature combined with the public key, proves the transaction was created by the real
     * owner of the address in question. Signature allows only the owner to spend their coins.
     * Signature prevents others from tampering with their submitted transaction before
     * a new block is mined (at the point of entry).
     */
    public final byte[] signature;

    public List<iTransactionInput> inputs;
    public List<iPendingTransaction> outputs = new ArrayList<>();

    // Constructor:
    public InOutTransaction(iWallet from, PublicKey to, BigDecimal amount, List<iTransactionInput> inputs, Environment env) {
        this.sender = from.getPublicKey();
        this.recipient = to;
        this.amount = amount;
        this.inputs = inputs;
        this.env = env;
        this.transactionId = calculateHash();
        this.signature = from.generateSignature(amount, to);
        verifiySignature();
    }

    private String calculateHash() {
        Long date = System.currentTimeMillis();
        if (env.lastTransactionTimestamp.get() == new Date().getTime()) {
            env.transactionCount.incrementAndGet();   //increase the sequence to avoid 2 identical transactions having the same transactionId
        } else {
            env.transactionCount.set(0);
            env.lastTransactionTimestamp.set(date);
        }
        return env.hashAlgo.apply(
                getStringFromKey(sender) +
                        getStringFromKey(recipient) +
                        amount.toString() + transactionUnifier()
        );
    }

    /**
     * This method should return value to make transaction transactionId unique in guarantied way
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
        String data = getStringFromKey(sender) + getStringFromKey(recipient) + amount.toString();
        return env.signature.verify(sender, data, signature);
    }

    @Override
    public boolean processTransaction() {

        if (verifiySignature() == false) {
            System.out.println("#Transaction Signature failed to verify");
            return false;
        }

        //gather transaction inputs (Make sure they are unspent):
        for (iTransactionInput input : inputs) {
            input.setUTXO(env.blockchain.getPendingTransactions().get(input.getTransactionOutputId()));
        }

        //check if transaction is valid having amount bigger than minimum for blockchain:
        if (getInputsValue().compareTo(env.minimumTransactionAmount) == -1) {
            log.warn("#Transaction Inputs to small: " + getInputsValue());
            return false;
        }

        //generate transaction outputs:
        BigDecimal leftOver = getInputsValue().subtract(amount); //get amount of inputs then the left over change:
        outputs.add(new TransactionOutput(this.recipient, amount, transactionId, env)); //send amount to recipient
        outputs.add(new TransactionOutput(this.sender, leftOver, transactionId, env)); //send the left over 'change' back to sender

        //add outputs to Unspent list
        for (iPendingTransaction output : outputs) {
            env.blockchain.getPendingTransactions().put(output.getId(), output);
        }

        //remove transaction inputs from UTXO lists as spent:
        for (iTransactionInput input : inputs) {
            if (input.getUTXO() == null) continue; //if Transaction can't be found skip it
            env.blockchain.getPendingTransactions().remove(input.getUTXO().getId());
        }

        return true;
    }

    /**
     * returns sum of inputs(UTXOs) values
     */
    @Override
    public BigDecimal getInputsValue() {
        BigDecimal total = new BigDecimal("0");
        for (iTransactionInput i : inputs) {
            if (i.getUTXO() == null) continue; //if Transaction can't be found skip it
            total.add(i.getUTXO().getAmount());
        }
        return total;
    }

    /**
     * returns sum of outputs:
     */
    @Override
    public BigDecimal getOutputsValue() {
        BigDecimal total = new BigDecimal("0");
        for (iPendingTransaction o : outputs) {
            total.add(o.getAmount());
        }
        return total;
    }

    @Override
    public List<iPendingTransaction> getOutputs() {
        return outputs;
    }
    @Override
    public PublicKey getSender() {
        return sender;
    }
    @Override
    public PublicKey getRecipient() {
        return recipient;
    }
    @Override
    public BigDecimal getAmount() {
        return amount;
    }
    @Override
    public String getTransactionId() {
        return transactionId;
    }
}
