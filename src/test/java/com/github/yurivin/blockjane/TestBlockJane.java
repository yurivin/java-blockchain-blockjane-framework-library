package com.github.yurivin.blockjane;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.serializers.CollectionSerializer;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

public class TestBlockJane {

    private final Logger LOG = Logger.getLogger(this.getClass().getName());
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void mainTest() throws InterruptedException, JsonProcessingException {
        Environment env = new Environment();
        BlockJane blockJane = new BlockJane(env);
        blockJane.run();
        blockJane.addBlockData("Test block data");
        blockJane.addBlockData("Other test block data");
        Thread.sleep(200);
        blockJane.setRunning(false);
        Assert.assertNotNull(blockJane.getEnv().blockchain.getLastBlock());
        Assert.assertTrue(blockJane.getEnv().blockchain.isCachedChainValid());
    }

}
