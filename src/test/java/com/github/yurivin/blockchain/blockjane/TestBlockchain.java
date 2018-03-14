package com.github.yurivin.blockchain.blockjane;

import com.github.yurivin.blockchain.blockjane.hash.algorithm.SHA256;
import org.junit.Test;

public class TestBlockchain {

    @Test
    public void mainTest() {
        BlockJane blockJane = new BlockJane(new SHA256());

    }
}
