package com.github.yurivin.blockjane;

import com.github.yurivin.blockjane.block.Block;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.block.GenesisBlock;
import org.junit.Assert;
import org.junit.Test;

public class TestBlock {

    @Test
    public void testBlock() {
        Environment env = new Environment();
        Block genesisBlock = new GenesisBlock(env, "Hi I'm a first block");
        Block block = new Block("Hi I'm second block", genesisBlock);
        Assert.assertEquals(genesisBlock.getHash(), block.getPreviousBlock().getHash());
    }
}
