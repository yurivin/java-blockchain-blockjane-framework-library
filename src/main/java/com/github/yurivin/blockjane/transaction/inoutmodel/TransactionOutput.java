package com.github.yurivin.blockjane.transaction.inoutmodel;

import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.transaction.iTransactionOutput;

import static com.github.yurivin.blockjane.utils.StringUtils.getStringFromKey;


import java.math.BigDecimal;
import java.security.PublicKey;

public class TransactionOutput implements iTransactionOutput {

    private final String id;
    private final PublicKey recipient; //the amount of coins they own
    private final String parentTransactionId; //the id of the transaction this output was created in
    private final BigDecimal amount;

    //Constructor
    public TransactionOutput(PublicKey recipient, BigDecimal amount, String parentTransactionId, Environment env) {
        this.recipient = recipient;
        this.amount = amount;
        this.parentTransactionId = parentTransactionId;
        this.id = env.hashAlgo.apply(getStringFromKey(recipient) + amount.toString() + parentTransactionId);
    }

    /**
     *     Check if coin belongs to some publicKey
     */
    @Override
    public boolean isAssignedTo(PublicKey publicKey) {
        return (publicKey == recipient);
    }
    @Override
    public String getId() {
        return id;
    }
    @Override
    public PublicKey getRecipient() {
        return recipient;
    }
    @Override
    public String getParentTransactionId() {
        return parentTransactionId;
    }
    @Override
    public BigDecimal getAmount() {
        return amount;
    }
}
