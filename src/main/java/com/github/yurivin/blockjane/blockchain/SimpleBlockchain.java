package com.github.yurivin.blockjane.blockchain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yurivin.blockjane.block.Block;
import com.github.yurivin.blockjane.block.GenesisBlock;
import com.github.yurivin.blockjane.block.iBlock;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.transaction.iPendingTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SimpleBlockchain implements iBlockchain {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * This property should be not accessible out from this class.
     * It will cause blockchain integrity violation because blocks hash
     * inheritance will be broken.
     */
    private final List<iBlock> blocksCache;
    public Environment env;
    /**
     * This property should be not accessible out from this class.
     * It will cause blockchain integrity violation because blocks hash
     * inheritance will be broken.
     */
    private iBlock lastBlock;
    /**
     * Queue of data to add in to new blocks;
     */
    private Queue<String> blockDataQueue = new ConcurrentLinkedQueue<>();
    /**
     * List of all unspent transactions.
     */
    private final Map<String, iPendingTransaction> UTXOs = new HashMap<>();


    public SimpleBlockchain() {
        this.blocksCache = new ArrayList<>();
    }


    @Override
    public boolean newBlock() throws JsonProcessingException {
        iBlock newBlock = null;
        if (lastBlock == null) {
            newBlock = new GenesisBlock(env, "Genesis block data");
        } else {
            newBlock = new Block(blockDataQueue.poll(), lastBlock, env);
            log.info("New block created: " + mapper.writeValueAsString(newBlock));
        }
        lastBlock = newBlock;
        blocksCache.add(newBlock);
        return serialize(newBlock);
    }

    private boolean serialize(iBlock newBlock) {
        boolean serialized = false;
        List<iBlock> toRemoveList = new ArrayList<>();
        if (blocksCache.size() > 0) {
            for (iBlock block : blocksCache) {
                serialized = env.blockSerializer.serialize(newBlock);
                if (!serialized) {
                    break;
                } else {
                    toRemoveList.add(block);
                }
            }
            blocksCache.removeAll(toRemoveList);
        }
        return serialized;
    }

    @Override
    public Environment setEnvironment(Environment env) {
        this.env = env;
        return this.env;
    }

    @Override
    public iBlock getLastBlock() {
        return lastBlock;
    }

    /**
     * Method to check chain integrity.
     *
     * @return
     */
    @Override
    public Boolean isCachedChainValid() {
        return env.blockSerializer.isChainValid(blocksCache);
    }

    @Override
    public void addBlockData(String data) {
        blockDataQueue.add(data);
    }

    @Override
    public Map<String, iPendingTransaction> getPendingTransactions() {
        return UTXOs;
    }


}
