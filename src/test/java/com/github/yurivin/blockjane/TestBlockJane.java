package com.github.yurivin.blockjane;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.yurivin.blockjane.identity.PublicKeyIdentity;
import com.github.yurivin.blockjane.identity.iIdentity;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.utils.Entry;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestBlockJane {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void mainTest() throws InterruptedException {

        //Test Blockchain run
        Environment env = new Environment();
        BlockJane blockJane = new BlockJane(env);
        blockJane.run();
        //Test add data to the block
        blockJane.addBlockData(new Entry("testData", env.jsonMapper.convertValue("Test block data", JsonNode.class)));
        blockJane.addBlockData(new Entry("testData", env.jsonMapper.convertValue("Other Test block data", JsonNode.class)));
        Thread.sleep(300);
        blockJane.stop();

        //Assert blocks were created
        Assert.assertNotNull(blockJane.getEnv().blockchain.getLastBlock());
        //Assert that chain is valid. All blocks hashes are chained.
        Assert.assertTrue(blockJane.getEnv().blockchain.isCachedChainValid());
        //Test identity creation
        iIdentity wallet = blockJane.createIdentity();
        Assert.assertNotNull(wallet);
        Assert.assertEquals(wallet.getClass(), PublicKeyIdentity.class);
    }

}
