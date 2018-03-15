package com.github.yurivin.blockjane.infrastracture;

public class GenesisBlock extends Block {
    /**
     * Different class for genesis block created to exclude of possibility
     * create a regular block with previous hash different from previous block hash.
     * @param env
     * @param data
     * @param genesisHash
     */
    public GenesisBlock(Environment env, String data, String genesisHash) {
        super(env, data, genesisHash);
    }

}
