package com.github.yurivin.blockjane.blockchain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.yurivin.blockjane.block.GenesisBlock;
import com.github.yurivin.blockjane.block.iBlock;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.utils.Entry;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

public class TestBlockchain {

    @Test
    public void testNewBlock() throws JsonProcessingException {
        Environment env = new Environment();
        env.blockchain.addBlockData(new Entry("testData", env.jsonMapper.convertValue("Test block data", JsonNode.class)));
        iBlock block = env.blockchain.newBlock();
        Assert.assertEquals(block.getClass(), GenesisBlock.class);
        block = env.blockchain.newBlock();
        assertNotNull(block);
        assertEquals(block.getData().toString().intern(), "{\"testData\":\"Test block data\"}".intern());
        //Test block without data;
        block = env.blockchain.newBlock();
        assertNotNull(block);
        assertNull(block.getData());
    }
}
