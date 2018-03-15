package com.github.yurivin.blockjane.blockchain;

import com.github.yurivin.blockjane.infrastracture.Block;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.infrastracture.GenesisBlock;

import java.util.concurrent.ArrayBlockingQueue;

public class DummyBlockchain implements iBlockchain {

    private final ArrayBlockingQueue<Block> serializedBlocks;

    public Environment env;
    /**
     * This property should be not accessible out from this class.
     * It will cause blockchain integrity violation because blocks hash
     * inheritance will be broken.
     */
    private Block lastBlock;
    public DummyBlockchain() {
        this.serializedBlocks = new ArrayBlockingQueue<>(4);
    }


    @Override
    public boolean newBlock() {
        Block newBlock = null;
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


}
