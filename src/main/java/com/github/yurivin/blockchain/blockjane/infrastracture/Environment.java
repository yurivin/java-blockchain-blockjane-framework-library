package com.github.yurivin.blockchain.blockjane.infrastracture;

import com.github.yurivin.blockchain.blockjane.hash.algorithm.iAlgo;

public class Environment {

    public Environment (iAlgo algo) {
        this.hashAlgo = algo;
    }
    public final iAlgo hashAlgo;

}
