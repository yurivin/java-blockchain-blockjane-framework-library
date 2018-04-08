package com.github.yurivin.blockjane.block;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.yurivin.blockjane.block.Block;
import com.github.yurivin.blockjane.block.iBlock;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.block.GenesisBlock;
import org.junit.Assert;
import org.junit.Test;

public class TestBlock {

    @Test
    public void testBlock() {
        Environment env = new Environment();
        ObjectNode data = env.jsonMapper.createObjectNode();
        data.set("testData", env.jsonMapper.convertValue("Hi I'm a first block", JsonNode.class));
        Block genesisBlock = new GenesisBlock(env,data);
        data = env.jsonMapper.createObjectNode();
        data.set("testData", env.jsonMapper.convertValue("Hi I'm second block", JsonNode.class));
        iBlock block = new Block(data, genesisBlock);
        Assert.assertEquals(genesisBlock.getHash(), block.getPreviousBlock().getHash());
    }
}
