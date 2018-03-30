package com.github.yurivin.blockjane.transaction;

import java.math.BigDecimal;
import java.security.PrivateKey;

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
}
