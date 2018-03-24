package com.github.yurivin.blockjane.infrastracture;

import com.github.yurivin.blockjane.blockchain.SimpleBlockchain;
import com.github.yurivin.blockjane.blockchain.iBlockchain;
import com.github.yurivin.blockjane.consensus.PoWConsensus;
import com.github.yurivin.blockjane.consensus.iConsensus;
import com.github.yurivin.blockjane.hash.SHA256;
import com.github.yurivin.blockjane.hash.iAlgo;
import com.github.yurivin.blockjane.serializers.CollectionSerializer;
import com.github.yurivin.blockjane.serializers.iBlockSerializer;
import com.github.yurivin.blockjane.transaction.signature.ECDSASignature;
import com.github.yurivin.blockjane.transaction.signature.iSignature;
import com.github.yurivin.blockjane.wallet.PublicKeyWallet;
import com.github.yurivin.blockjane.wallet.iWallet;

import java.security.PublicKey;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Environment {

    public Environment () {
        this.hashAlgo = new SHA256();
        setBlockchain(new SimpleBlockchain());
        this.blockSerializer = new CollectionSerializer(40);
        this.setConsensus(new PoWConsensus());
        this.walletType = PublicKeyWallet.class;
        this.signature = new ECDSASignature();
    }

    public Environment (iAlgo algo, iBlockchain blockchain, iBlockSerializer blockSerializer, iConsensus consensus,
                        Class walletType, iSignature signature) {
        this.hashAlgo = algo;
        this.blockchain = blockchain;
        this.blockSerializer = blockSerializer;
        this.consensus = consensus;
        this.walletType = walletType;
        this.signature = signature;
    }

    public iAlgo hashAlgo;
    public iBlockchain blockchain;
    public iBlockSerializer blockSerializer;
    public iConsensus consensus;
    public Class walletType;
    public Map<PublicKey,iWallet> wallets;
    public final AtomicLong lastTransactionTimestamp = new AtomicLong(0);
    public final AtomicInteger transactionCount = new AtomicInteger(0);
    public Long nodeStartupTime = System.currentTimeMillis();
    public UUID nodeStartupUuid = UUID.randomUUID();
    public iSignature signature;


    public void setBlockchain(iBlockchain blockchain) {
        this.blockchain = blockchain;
        blockchain.setEnvironment(this);
    }

    public void setConsensus(iConsensus consensus) {
        this.consensus = consensus;
        consensus.setEnvironment(this);
    }
}
