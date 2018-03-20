package com.github.yurivin.blockjane.infrastracture;

import com.github.yurivin.blockjane.blockchain.SimpleBlockchain;
import com.github.yurivin.blockjane.consensus.PoWConsensus;
import com.github.yurivin.blockjane.consensus.iConsensus;
import com.github.yurivin.blockjane.hash.SHA256;
import com.github.yurivin.blockjane.hash.iAlgo;
import com.github.yurivin.blockjane.serializers.CollectionSerializer;
import com.github.yurivin.blockjane.serializers.iBlockSerializer;
import com.github.yurivin.blockjane.blockchain.iBlockchain;

public class Environment {

    public Environment () {
        this.hashAlgo = new SHA256();
        setBlockchain(new SimpleBlockchain());
        this.blockSerializer = new CollectionSerializer(40);
        this.setConsensus(new PoWConsensus());
    }

    public Environment (iAlgo algo, iBlockchain blockchain, iBlockSerializer blockSerializer, iConsensus consensus) {
        this.hashAlgo = algo;
        this.blockchain = blockchain;
        this.blockSerializer = blockSerializer;
        this.consensus = consensus;
    }

    public iAlgo hashAlgo;
    public iBlockchain blockchain;
    public iBlockSerializer blockSerializer;
    public iConsensus consensus;


    public void setBlockchain(iBlockchain blockchain) {
        this.blockchain = blockchain;
        blockchain.setEnvironment(this);
    }

    public void setConsensus(iConsensus consensus) {
        this.consensus = consensus;
        consensus.setEnvironment(this);
    }
}
