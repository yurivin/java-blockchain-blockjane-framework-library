package com.github.yurivin.blockjane.transaction;

public interface iTransactionInput {

    String getTransactionOutputId();
    iTransactionOutput getUTXO();

    /**
     * Usually expects that it is possible to set UTXO only once.
     * And no possibility to change it after it is done already.
     * @param UTXO
     */
    void setUTXO(iTransactionOutput UTXO);
}
