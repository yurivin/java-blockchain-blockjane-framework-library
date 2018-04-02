package com.github.yurivin.blockjane.transaction;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.List;

public interface iTransaction {

    /**
     *     Verifies the data we signed hasn't been tampered with
     */
    boolean verifiySignature();
    /**
     *     returns sum of outputs:
     */
    BigDecimal getOutputsValue();

    /**
     *     returns sum of inputs(UTXOs) values
     */
    public BigDecimal getInputsValue();

    /**
     * method to process transaction during proof.
     * @return
     */
    boolean processTransaction();

    /**
     * Method to get/add UTXO's
     * @return
     */
    List<iTransactionOutput> getOutputs();

    PublicKey getSender();

    PublicKey getRecipient();

    BigDecimal getAmount();

    String getTransactionId();

    List<iTransactionInput> getInputs();
}
