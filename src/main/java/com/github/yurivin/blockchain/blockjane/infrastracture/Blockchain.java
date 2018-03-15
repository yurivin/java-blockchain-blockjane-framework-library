package com.github.yurivin.blockchain.blockjane.infrastracture;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Blockchain {

    Environment env;
    public Blockchain(Environment env) {
        this.env = env;
    }

    /**
     * Method to add block in to blockchain.
     * Should return 'true' if all pending blocks successfully recorded to database.
     * If there is some problem with DB connection, blockchain works properly,
     * but records all pending block to DB when DB connection was restored.
     * @param block
     * @return
     */
    public boolean addBlock(Block block) {

    }


}
