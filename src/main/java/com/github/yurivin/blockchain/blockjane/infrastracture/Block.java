package com.github.yurivin.blockchain.blockjane.infrastracture;

import lombok.Data;

import java.util.Date;
@Data
public class Block {

    public Block(Environment env, String data, String previousHash) {
        this.env = env;
        this.timeStamp = new Date().getTime();
        this.data = data;
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }
    private final String hash;
    private final String previousHash;
    private final String data; //our data will be a simple message.
    private final long timeStamp; //as number of milliseconds since 1/1/1970 in UTC.
    private final Environment env;

    //Block Constructor.

    private String calculateHash() {
        String calculatedHash = env.hashAlgo.apply(
                previousHash +
                        Long.toString(timeStamp) +
                        data
        );
        return calculatedHash;
    }
}
