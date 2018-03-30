package com.github.yurivin.blockjane.infrastracture;

import com.github.yurivin.blockjane.blockchain.SimpleBlockchain;
import com.github.yurivin.blockjane.blockchain.iBlockchain;
import com.github.yurivin.blockjane.proof.PoW;
import com.github.yurivin.blockjane.proof.iProof;
import com.github.yurivin.blockjane.hash.SHA256;
import com.github.yurivin.blockjane.hash.iAlgo;
import com.github.yurivin.blockjane.serializers.CollectionSerializer;
import com.github.yurivin.blockjane.serializers.iBlockSerializer;
import com.github.yurivin.blockjane.signature.ECDSASignature;
import com.github.yurivin.blockjane.signature.iSignature;
import com.github.yurivin.blockjane.transaction.iTransactionEnvironment;
import com.github.yurivin.blockjane.wallet.PublicKeyWallet;
import com.github.yurivin.blockjane.wallet.iWallet;

import java.math.BigDecimal;
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
        this.setConsensus(new PoW());
        this.walletType = PublicKeyWallet.class;
        this.signature = new ECDSASignature();
    }

    public Environment (iAlgo algo, iBlockchain blockchain, iBlockSerializer blockSerializer, iProof consensus,
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
    public iProof consensus;
    public Class walletType;
    public Map<PublicKey,iWallet> wallets;
    public final AtomicLong lastTransactionTimestamp = new AtomicLong(0);
    public final AtomicInteger transactionCount = new AtomicInteger(0);
    public Long nodeStartupTime = System.currentTimeMillis();
    public UUID nodeStartupUuid = UUID.randomUUID();
    public iSignature signature;
    public BigDecimal minimumTransactionAmount = new BigDecimal(0.1f);
    public iTransactionEnvironment transactionEnv;

    public void setBlockchain(iBlockchain blockchain) {
        this.blockchain = blockchain;
        blockchain.setEnvironment(this);
    }

    public void setConsensus(iProof consensus) {
        this.consensus = consensus;
        consensus.setEnvironment(this);
    }
}
