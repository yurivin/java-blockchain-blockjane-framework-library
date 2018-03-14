package com.github.yurivin.blockchain.blockjane;

import com.github.yurivin.blockchain.blockjane.hash.algorithm.SHA256;
import com.github.yurivin.blockchain.blockjane.infrastracture.Block;
import com.github.yurivin.blockchain.blockjane.infrastracture.Environment;
import org.junit.Assert;
import org.junit.Test;

public class TestBlock {

    @Test
    public void testBlock() {
        String genesisHash = "0";
        Block block = new Block(new Environment(new SHA256()), "Hi I'm a first block", "0");
        Assert.assertEquals("0", block.getPreviousHash());
    }
}
