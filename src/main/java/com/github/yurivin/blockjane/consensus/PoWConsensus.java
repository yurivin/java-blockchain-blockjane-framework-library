package com.github.yurivin.blockjane.consensus;

import com.github.yurivin.blockjane.infrastracture.Environment;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class PoWConsensus implements iConsensus {

    /**
     * The "nonce"  field whose value is set so that the hash of the block will contain a run of leading zeros.
     */
    private int nonce;
    private Environment env;
    private String blockData;

    @Override
    public String generateConsensus() {
        int difficulty = calculateDifficulty();
        String hash = null;
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while (hash == null || !hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        log.info("Block Mined!!! : " + hash);
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
        if(env.blockchain.getLastBlock() != null) {
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

    private int calculateDifficulty() {
        return 3;
    }
}
