package com.github.yurivin.blockjane.transaction;

import java.util.Map;

public interface iTransactionEnvironment {

    Map<String, iPendingTransaction> getPending();

}
