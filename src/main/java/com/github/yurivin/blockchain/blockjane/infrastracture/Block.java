package com.github.yurivin.blockchain.blockjane.infrastracture;

import java.util.Date;

public class Block {

    public Block(Environment env) {
        this.env = env;
    }
    public final String hash;
    public final String previousHash;
    private final String data; //our data will be a simple message.
    private final long timeStamp; //as number of milliseconds since 1/1/1970 in UTC.
    public final Environment env;

    //Block Constructor.
    public Block(String data,String previousHash ) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
    }

    public String calculateHash() {
        String calculatedhash = env.hashAlgo.apply(
                previousHash +
                        Long.toString(timeStamp) +
                        data
        );
        return calculatedhash;
    }
}
