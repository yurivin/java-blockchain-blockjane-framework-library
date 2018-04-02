package com.github.yurivin.blockjane;

import com.github.yurivin.blockjane.infrastracture.Chaining;
import com.github.yurivin.blockjane.infrastracture.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockJane {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private boolean running;
    private final Environment env;

    public BlockJane(Environment env) {
        if(env == null) {
            throw new IllegalStateException("BlockJane environment should be not null");
        }
        this.env = env;
    }

    public void run() {
        running = true;
        Thread chaining = new Thread(new Chaining(this));
        chaining.start();
    }

    public void addBlockData(String data) {
        env.blockchain.addBlockData(data);
    }

    public boolean isRunning() {
        return running;
    }

    public Environment getEnv() {
        return env;
    }

    public void stop() {
        running = false;
    }
}
