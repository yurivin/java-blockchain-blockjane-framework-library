package com.github.yurivin.blockjane.blockchain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.yurivin.blockjane.block.iBlock;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.transaction.iTransactionOutput;

import java.util.Map;

public interface iBlockchain {

    /**
     * Method to add block in to blockchain.
     * Should return 'true' if all pending blocks successfully serialized or saved in cash memory.
     * Saving in cache memory may can lead to the loss of blocks when the system falls.
     * If there is some problem with serialization, blockchain works properly,
     * but serializes all pending block when it is possible.
     * @return
     */
    boolean newBlock() throws JsonProcessingException;

    Environment setEnvironment(Environment env);
    iBlock getLastBlock();

    /**
     * Method to check the integrity of blockchain
     * @return
     */
    Boolean isCachedChainValid();

    /**
     * Method to add data to new block;
     * @param data
     */
    void addBlockData(String data);

    /**
     * get transactions awaiting inclusion in to block
     * @return
     */
    Map<String, iTransactionOutput> getPendingTransactions();
}
