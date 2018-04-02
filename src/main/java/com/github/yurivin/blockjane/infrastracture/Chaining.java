package com.github.yurivin.blockjane.infrastracture;

import com.github.yurivin.blockjane.BlockJane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Chaining implements Runnable {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private BlockJane blockJane;

    public Chaining(BlockJane blockJane) {
        this.blockJane = blockJane;
    }
    @Override
    public void run() {
        try {
            while (blockJane.isRunning()) {
                if (!blockJane.getEnv().blockchain.newBlock()) {
                    throw new IllegalStateException("Node is broken. Can't create block");
                }
            }
        } catch(Exception e) {
            log.error("Error in blockchain thread.", e);
        }
    }
}
