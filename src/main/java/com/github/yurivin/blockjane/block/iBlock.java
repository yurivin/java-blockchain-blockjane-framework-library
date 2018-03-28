package com.github.yurivin.blockjane.block;

import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.transaction.iTransaction;

import java.util.List;

public interface iBlock {

    String getHash();
    Environment getEnv();
    Long getId();
    iBlock getPreviousBlock();
    List<iTransaction> getTransactions();
}
