package com.github.yurivin.blockjane.blockchain;

import com.github.yurivin.blockjane.block.Block;
import com.github.yurivin.blockjane.block.iBlock;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.block.GenesisBlock;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
public class SimpleBlockchain implements iBlockchain {

    /**
     * This property should be not accessible out from this class.
     * It will cause blockchain integrity violation because blocks hash
     * inheritance will be broken.
     */
    private final List<iBlock> blocksCache;
    private final GsonBuilder jsonBuilder = new GsonBuilder();
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

    public SimpleBlockchain() {
        this.blocksCache = new ArrayList<>();
    }


    @Override
    public boolean newBlock() {
        iBlock newBlock = null;
        if(lastBlock == null) {
            newBlock = new GenesisBlock(env, "Genesis block data");
        } else {
            newBlock = new Block("Data for block:" + lastBlock.getId(), lastBlock, env);
            log.debug("New block created: " + jsonBuilder.setPrettyPrinting().create().toJson(newBlock));
        }
        lastBlock = newBlock;
        blocksCache.add(newBlock);
        return serialize(newBlock);
    }

    private boolean serialize(iBlock newBlock) {
        boolean serialized = false;
        List<iBlock> toRemoveList = new ArrayList<>();
        if(blocksCache.size() > 0) {
            for (iBlock block: blocksCache) {
                serialized = env.blockSerializer.serialize(newBlock);
                if(!serialized) { break;}
                else {toRemoveList.add(block);}
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
     * @return
     */
    @Override
    public Boolean isCachedChainValid() {
        return env.blockSerializer.isChainValid(blocksCache);
    }

    @Override
    public void addBlockData(String data) {

    }


}
