package com.github.yurivin.blockjane;

import com.github.yurivin.blockjane.hash.algorithm.iAlgo;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.serializers.iBlockSerializer;
import com.github.yurivin.blockjane.blockchain.iBlockchain;
import lombok.Data;

@Data
public class BlockJane {

    private boolean isRuning;
    public BlockJane(Environment env) {
        this.env = env;
    }

    private final Environment env;

    public void run() {
        isRuning = true;
        while(isRuning) {
            if(!env.getBlockchain().newBlock());
            throw new IllegalStateException("Node is broken. Can't create block");
        }
    }



}
