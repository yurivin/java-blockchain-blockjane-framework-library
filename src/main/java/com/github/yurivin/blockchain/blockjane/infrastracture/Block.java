package com.github.yurivin.blockchain.blockjane.infrastracture;

import java.util.Date;

public class Block {

    public Block(Environment env, long timeStamp, String data, String previousHash, String hash) {
        this.env = env;
        this.timeStamp = timeStamp;
        this.data = data;
        this.previousHash = previousHash;
        this.hash = hash;
    }
    private final String hash;
    private final String previousHash;
    private final String data; //our data will be a simple message.
    private final long timeStamp; //as number of milliseconds since 1/1/1970 in UTC.
    private final Environment env;

    //Block Constructor.

    public String calculateHash() {
        String calculatedhash = env.hashAlgo.apply(
                previousHash +
                        Long.toString(timeStamp) +
                        data
        );
        return calculatedhash;
    }
}
