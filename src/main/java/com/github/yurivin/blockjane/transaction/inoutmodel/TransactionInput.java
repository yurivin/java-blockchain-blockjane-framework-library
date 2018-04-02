package com.github.yurivin.blockjane.transaction.inoutmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.transaction.iTransactionInput;
import com.github.yurivin.blockjane.transaction.iTransactionOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Transaction inputs are references to previous transaction outputs.
 */
public class TransactionInput implements iTransactionInput {
    @JsonIgnore
    private Environment env;
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * The transactionOutputId will be used to find the relevant TransactionOutput,
     * allowing proof to check your ownership.
     */
    private final String transactionOutputId; //Reference to TransactionOutputs -> transactionId
    /**
     * Contains the Unspent transaction output. Should be set only once during transaction creation.
     * Your wallets balance is the sum of all the unspent transaction outputs addressed to you.
     * Follow bitcoins convention and call unspent transaction outputs: UTXO
     */
    private iTransactionOutput UTXO;
    /**
     * Check that this UTXO was not set earlier;
     */
    private boolean isUTXOsetOnce;

    public TransactionInput(String transactionOutputId) {
        this.transactionOutputId = transactionOutputId;
    }

    @Override
    public String getTransactionOutputId() {
        return transactionOutputId;
    }

    @Override
    public iTransactionOutput getUTXO() {
        return UTXO;
    }

    @Override
    public void setUTXO(iTransactionOutput UTXO) {
        if (!isUTXOsetOnce) {
            this.UTXO = UTXO;
        } else {
            log.warn("Attempt to set new value to already existing UTXO");
        }
    }
}