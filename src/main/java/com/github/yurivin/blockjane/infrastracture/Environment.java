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
import com.github.yurivin.blockjane.identity.PublicKeyIdentity;
import com.github.yurivin.blockjane.identity.iIdentity;

import java.security.PublicKey;
import java.util.Map;
import java.util.UUID;

public class Environment {

    public Environment () {
        this.hashAlgo = new SHA256();
        setBlockchain(new SimpleBlockchain());
        this.blockSerializer = new CollectionSerializer(40);
        this.setProofType(new PoW());
        this.identityType = PublicKeyIdentity.class;
        this.signature = new ECDSASignature();
    }

    public Environment (iAlgo algo, iBlockchain blockchain, iBlockSerializer blockSerializer, iProof proofType,
                        Class identityType, iSignature signature) {
        this.hashAlgo = algo;
        this.blockchain = blockchain;
        this.blockSerializer = blockSerializer;
        this.proofType = proofType;
        this.identityType = identityType;
        this.signature = signature;
    }

    public iAlgo hashAlgo;
    public iBlockchain blockchain;
    public iBlockSerializer blockSerializer;
    public iProof proofType;
    public Class identityType;
    public Map<PublicKey,iIdentity> identities;
    public Long nodeStartupTime = System.currentTimeMillis();
    public UUID nodeStartupUuid = UUID.randomUUID();
    public iSignature signature;

    public void setBlockchain(iBlockchain blockchain) {
        this.blockchain = blockchain;
        blockchain.setEnvironment(this);
    }

    public void setProofType(iProof proofType) {
        this.proofType = proofType;
        proofType.setEnvironment(this);
    }
}
