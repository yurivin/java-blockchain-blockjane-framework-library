package com.github.yurivin.blockjane.blockchain;

import com.github.yurivin.blockjane.block.Block;
import com.github.yurivin.blockjane.block.iBlock;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.block.GenesisBlock;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SimpleBlockchain implements iBlockchain {

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
        //TODO realize adding data to block;
    }


}
