package com.github.yurivin.blockjane.proof;

import com.github.yurivin.blockjane.infrastracture.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class PoW implements iProof {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * The "nonce"  field whose value is set so that the hash of the block will contain a run of leading zeros.
     */
    private int nonce;
    private Environment env;
    private String blockData;
    private volatile int delayFactor = 3;

    @Override
    public String proof() {
        String hash = null;
        String target = new String(new char[delayFactor]).replace('\0', '0'); //Create a string with difficulty * "0"
        while (hash == null || !hash.substring(0, delayFactor).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        log.info("Block hash found: " + hash);
        return hash;
    }

    @Override
    public void setBlockData(String data) {
        this.blockData = data;
    }

    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
    }

    private String calculateHash() {
        String calculatedHash = "0";
        if (env.blockchain.getLastBlock() != null) {
            calculatedHash = env.hashAlgo.apply(
                    env.blockchain.getLastBlock().getHash() +
                            Long.toString(new Date().getTime()) +
                            nonce +
                            blockData
            );
        } else {
            calculatedHash = env.hashAlgo.apply(
                    Long.toString(new Date().getTime()) +
                            nonce +
                            blockData
            );
        }
        return calculatedHash;
    }

    @Override
    public int getDelayFactor() {
        return delayFactor;
    }

    @Override
    public void setDelayFactor(int delayFactor) {
        this.delayFactor = delayFactor;
    }
}
