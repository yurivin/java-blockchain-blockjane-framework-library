package com.github.yurivin.blockjane.block;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.yurivin.blockjane.block.Block;
import com.github.yurivin.blockjane.infrastracture.Environment;

public class GenesisBlock extends Block {
    /**
     * Different class for genesis block created to exclude of possibility
     * create a regular block with previous hash different from previous block transactionHash.
     * @param env
     * @param data
     */
    public GenesisBlock(Environment env, ObjectNode data) {
        super(env, data);
    }

}
