package com.github.yurivin.blockjane.block;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.transaction.iTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Block implements iBlock {

    private final Logger log = LoggerFactory.getLogger(getClass());

    protected Block(Environment env, String data) {
        this.env = env;
        this.timeStamp = new Date().getTime();
        this.data = data;
        this.previousBlock = null;
        this.hash = env.consensus.generateConsensus();
        this.id = 1L;
    }

    public Block(String data, iBlock previousBlock) {
        this.env = previousBlock.getEnv();
        this.timeStamp = new Date().getTime();
        this.data = data;
        this.previousBlock = previousBlock;
        this.hash = previousBlock.getEnv().consensus.generateConsensus();
        this.id = previousBlock.getId() + 1;
    }

    public Block(String data, iBlock previousBlock, Environment env) {
        this.env = env != null ? env : previousBlock.getEnv();
        this.timeStamp = new Date().getTime();
        this.data = data;
        this.previousBlock = previousBlock;
        env.consensus.setBlockData(data);
        this.hash = env.consensus.generateConsensus();
        this.id = previousBlock.getId() + 1;
    }

    private final Long id;
    private final String hash;
    @JsonIgnore
    private final iBlock previousBlock;
    private final String data; //our data will be a simple message.
    private final long timeStamp; //as number of milliseconds since 1/1/1970 in UTC.
    @JsonIgnore
    private final Environment env;
    private final List<iTransaction> transactions = new ArrayList<>();


    @Override
    public String getHash() {
        return hash;
    }

    @Override
    public Environment getEnv() {
        return env;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public iBlock getPreviousBlock() {
        return previousBlock;
    }

    @Override
    public List<iTransaction> getTransactions() {
        return transactions;
    }
}
