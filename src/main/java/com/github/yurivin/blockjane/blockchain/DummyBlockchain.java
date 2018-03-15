package com.github.yurivin.blockjane.blockchain;

import com.github.yurivin.blockjane.block.Block;
import com.github.yurivin.blockjane.block.iBlock;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.block.GenesisBlock;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DummyBlockchain implements iBlockchain {

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
    private Block lastBlock;

    public DummyBlockchain() {
        this.blocksCache = new ArrayList<>();
    }


    @Override
    public boolean newBlock() {
        iBlock newBlock = null;
        if(lastBlock == null) {
            newBlock = new GenesisBlock(env, "Genesis block data", "0");
        } else {
            newBlock = new Block("Data for block:" + lastBlock.getId(), lastBlock, env);

        }
        return env.blockSerializer.serialize(newBlock);

    }

    @Override
    public Environment setEnvironment(Environment env) {
        this.env = env;
        return this.env;
    }

    /**
     * Method to check chain integrity.
     * @return
     */
    @Override
    public Boolean isCachedChainValid() {
        return env.blockSerializer.isChainValid(blocksCache);
    }


}
