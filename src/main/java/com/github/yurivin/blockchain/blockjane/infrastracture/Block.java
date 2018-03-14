package com.github.yurivin.blockchain.blockjane.infrastracture;

import lombok.Data;

import java.util.Date;
@Data
public class Block {

    private Block(String genesisHash) {
        this.env = null;
        this.timeStamp = new Date().getTime();
        this.data = "Genesis Block";
        this.previousBlock = new Block(genesisHash);
        this.hash = genesisHash;
        this.id = 0L;
    }

    protected Block(Environment env, String data, String genesisHash) {
        this.env = env;
        this.timeStamp = new Date().getTime();
        this.data = data;
        this.previousBlock = this;
        this.hash = calculateHash();
        this.id = 1L;
    }

    public Block(String data, Block previousBlock) {
        this.env = previousBlock.getEnv();
        this.timeStamp = new Date().getTime();
        this.data = data;
        this.previousBlock = previousBlock;
        this.hash = calculateHash();
        this.id = previousBlock.id + 1;
    }

    public Block(String data, Block previousBlock, Environment env) {
        this.env = env != null ? env : previousBlock.getEnv();
        this.timeStamp = new Date().getTime();
        this.data = data;
        this.previousBlock = previousBlock;
        this.hash = calculateHash();
        this.id = previousBlock.id + 1;
    }

    private final Long id;
    private final String hash;
    private final Block previousBlock;
    private final String data; //our data will be a simple message.
    private final long timeStamp; //as number of milliseconds since 1/1/1970 in UTC.
    private final Environment env;

    //Block Constructor.

    private String calculateHash() {
        String calculatedHash = env.hashAlgo.apply(
                previousBlock.getHash() +
                        Long.toString(timeStamp) +
                        data
        );
        return calculatedHash;
    }
}
