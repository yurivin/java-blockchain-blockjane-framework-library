package com.github.yurivin.blockjane.infrastracture;

import com.github.yurivin.blockjane.hash.algorithm.iAlgo;
import com.github.yurivin.blockjane.serializers.iBlockSerializer;
import com.github.yurivin.blockjane.blockchain.iBlockchain;

public class Environment {

    public Environment (iAlgo algo, iBlockchain blockchain, iBlockSerializer blockSerializer) {
        this.hashAlgo = algo;
        this.blockchain = blockchain;
        this.blockSerializer = blockSerializer;
    }

    public iAlgo hashAlgo;

    public iBlockchain getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(iBlockchain blockchain) {
        this.blockchain = blockchain;
        blockchain.setEnvironment(this);
    }

    private iBlockchain blockchain;
    public iBlockSerializer blockSerializer;

}
