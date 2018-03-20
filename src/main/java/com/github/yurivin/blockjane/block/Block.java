package com.github.yurivin.blockjane.block;

import com.github.yurivin.blockjane.consensus.iConsensus;
import com.github.yurivin.blockjane.infrastracture.Environment;
import lombok.Data;

import java.util.Date;
@Data
public class Block implements iBlock {

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
    private final iBlock previousBlock;
    private final String data; //our data will be a simple message.
    private final long timeStamp; //as number of milliseconds since 1/1/1970 in UTC.
    private final Environment env;

}
