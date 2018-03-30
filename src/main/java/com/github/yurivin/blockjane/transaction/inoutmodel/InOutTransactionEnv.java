package com.github.yurivin.blockjane.transaction.inoutmodel;

import com.github.yurivin.blockjane.transaction.iPendingTransaction;
import com.github.yurivin.blockjane.transaction.iTransactionEnvironment;

import java.util.HashMap;
import java.util.Map;

public class InOutTransactionEnv implements iTransactionEnvironment {
    /**
     * List of all unspent transactions.
     */
    private final Map<String, iPendingTransaction> UTXOs = new HashMap<>();

    @Override
    public Map<String, iPendingTransaction> getPending() {
        return UTXOs;
    }
}
