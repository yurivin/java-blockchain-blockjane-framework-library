package com.github.yurivin.blockjane;

import com.github.yurivin.blockjane.infrastracture.Chaining;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.identity.iIdentity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.security.PublicKey;

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

    public iIdentity getWallet(PublicKey publicKey) {
        return env.identities.get(publicKey);
    }

    public iIdentity createWallet() {
        try {
            Method m = env.identityType.getMethod("instantiate", Environment.class);
            return (iIdentity)m.invoke(null,env);
        } catch (Exception e) {
            throw new RuntimeException("Error creating identity", e);
        }
    }

    public boolean isRunning() {
        return running;
    }

    public Environment getEnv() {
        return env;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
