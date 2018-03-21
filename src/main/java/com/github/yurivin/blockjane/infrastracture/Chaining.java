package com.github.yurivin.blockjane.infrastracture;

import com.github.yurivin.blockjane.BlockJane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Chaining implements Runnable {

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
