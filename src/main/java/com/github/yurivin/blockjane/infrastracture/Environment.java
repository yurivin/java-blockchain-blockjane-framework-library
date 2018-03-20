package com.github.yurivin.blockjane.infrastracture;

import com.github.yurivin.blockjane.blockchain.DummyBlockchain;
import com.github.yurivin.blockjane.consensus.PoWConsensus;
import com.github.yurivin.blockjane.consensus.iConsensus;
import com.github.yurivin.blockjane.hash.SHA256;
import com.github.yurivin.blockjane.hash.iAlgo;
import com.github.yurivin.blockjane.serializers.DummyCollectionSerializer;
import com.github.yurivin.blockjane.serializers.iBlockSerializer;
import com.github.yurivin.blockjane.blockchain.iBlockchain;

public class Environment {

    public Environment () {
        this.hashAlgo = new SHA256();
        setBlockchain(new DummyBlockchain());
        this.blockSerializer = new DummyCollectionSerializer(40);
        this.consensus = new PoWConsensus();
    }

    public Environment (iAlgo algo, iBlockchain blockchain, iBlockSerializer blockSerializer, iConsensus consensus) {
        this.hashAlgo = algo;
        this.blockchain = blockchain;
        this.blockSerializer = blockSerializer;
        this.consensus = consensus;
    }

    public iAlgo hashAlgo;
    private iBlockchain blockchain;
    public iBlockSerializer blockSerializer;
    public iConsensus consensus;

    public iBlockchain getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(iBlockchain blockchain) {
        this.blockchain = blockchain;
        blockchain.setEnvironment(this);
    }

}
