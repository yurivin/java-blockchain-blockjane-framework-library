package com.github.yurivin.blockchain.blockjane;

import com.github.yurivin.blockchain.blockjane.hash.algorithm.iAlgo;
import com.github.yurivin.blockchain.blockjane.infrastracture.Blockchain;
import com.github.yurivin.blockchain.blockjane.infrastracture.Environment;
import lombok.Data;

@Data
public class BlockJane {

    public BlockJane(iAlgo algo) {
        env = new Environment(algo);
        this.blockchain = new Blockchain(env);
    }

    Blockchain blockchain;

    private final Environment env;

    public void run() {

    }



}
