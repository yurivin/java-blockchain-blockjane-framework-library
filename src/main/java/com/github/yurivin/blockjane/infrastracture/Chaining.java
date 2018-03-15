package com.github.yurivin.blockjane.infrastracture;

import com.github.yurivin.blockjane.BlockJane;

public class Chaining implements Runnable {

    private BlockJane blockJane;

    public Chaining(BlockJane blockJane) {
        this.blockJane = blockJane;
    }
    @Override
    public void run() {
        while(blockJane.isRunning()) {
            if(!blockJane.getEnv().getBlockchain().newBlock()) {
                throw new IllegalStateException("Node is broken. Can't create block");
            }
        }
    }
}
