package com.github.yurivin.blockchain.blockjane;

import com.github.yurivin.blockchain.blockjane.hash.algorithm.iAlgo;
import com.github.yurivin.blockchain.blockjane.infrastracture.Environment;
import lombok.Data;

@Data
public class Blockchain {

    public Blockchain(iAlgo algo) {
        env = new Environment(algo);
    }

    private final Environment env;

    public void run() {

    }



}
