package com.github.yurivin.blockjane.transaction;

import java.math.BigDecimal;
import java.security.PublicKey;

public interface iPendingTransaction {

    boolean isAssignedTo(PublicKey publicKey);
    BigDecimal getAmount();
    String getParentTransactionId();
    PublicKey getRecipient();
    String getId();

}
