package com.github.yurivin.blockjane.consensus;

import com.github.yurivin.blockjane.infrastracture.Environment;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.Date;

@Data
@Slf4j
public class PoWConsensus implements iConsensus {

    private BigInteger nonce;
    private Environment env;
    private int difficulty;
    private String blockData;


    @Override
    public String hashPart() {
        return nonce.toString();
    }

    @Override
    public String generateConsensus() {
        String hash = "0";
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce.add(BigInteger.ONE);
            hash = calculateHash();
        }
        log.info("Block Mined!!! : " + hash);
        return hash;
    }

    private String calculateHash() {
        String calculatedHash = "0";
        if(env.getBlockchain().getLastBlock() != null) {
            calculatedHash = env.hashAlgo.apply(
                    env.getBlockchain().getLastBlock().getHash() +
                            Long.toString(new Date().getTime()) +
                            env.consensus.hashPart() +
                            blockData
            );
        }
        return calculatedHash;
    }
}
