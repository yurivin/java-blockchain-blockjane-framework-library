package com.github.yurivin.blockchain.blockjane;

import com.github.yurivin.blockchain.blockjane.hash.algorithm.SHA256;
import org.junit.Test;

public class TestBlockchain {

    @Test
    public void mainTest() {
        Blockchain blockchain = new Blockchain(new SHA256());

    }
}
