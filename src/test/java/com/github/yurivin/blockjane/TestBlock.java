package com.github.yurivin.blockjane;

import com.github.yurivin.blockjane.blockchain.DummyBlockchain;
import com.github.yurivin.blockjane.hash.algorithm.SHA256;
import com.github.yurivin.blockjane.hash.algorithm.iAlgo;
import com.github.yurivin.blockjane.infrastracture.Block;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.infrastracture.GenesisBlock;
import com.github.yurivin.blockjane.serializers.DummyCollectionSerializer;
import org.junit.Assert;
import org.junit.Test;

public class TestBlock {

    @Test
    public void testBlock() {
        String genesisHash = "0";
        Environment env = new Environment(new SHA256(), new DummyBlockchain(), new DummyCollectionSerializer(10));
        Block genesisBlock = new GenesisBlock(env, "Hi I'm a first block", genesisHash);
        Block block = new Block("Hi I'm second block", genesisBlock);
        Assert.assertEquals(genesisBlock.getHash(), block.getPreviousBlock().getHash());
    }
}
