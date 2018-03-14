package com.github.yurivin.blockchain.blockjane;

import com.github.yurivin.blockchain.blockjane.hash.algorithm.SHA256;
import com.github.yurivin.blockchain.blockjane.infrastracture.Block;
import com.github.yurivin.blockchain.blockjane.infrastracture.Environment;
import com.github.yurivin.blockchain.blockjane.infrastracture.GenesisBlock;
import org.junit.Assert;
import org.junit.Test;

public class TestBlock {

    @Test
    public void testBlock() {
        String genesisHash = "0";
        Block genesisBlock = new GenesisBlock(new Environment(new SHA256()), "Hi I'm a first block", genesisHash);
        Block block = new Block("Hi I'm second block", genesisBlock);
        Assert.assertEquals(genesisBlock.getHash(), block.getPreviousBlock().getHash());
    }
}
