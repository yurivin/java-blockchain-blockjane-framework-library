package com.github.yurivin.blockjane;

import com.github.yurivin.blockjane.infrastracture.Environment;
import org.junit.Assert;
import org.junit.Test;

public class TestBlockJane {

    @Test
    public void mainTest() throws InterruptedException {
        Environment env = new Environment();
        BlockJane blockJane = new BlockJane(env);
        blockJane.run();
        Thread.sleep(300);
        blockJane.addBlockData("Test block data");
        blockJane.addBlockData("Other test block data");
        blockJane.setRunning(false);
        Assert.assertNotNull(blockJane.getEnv().blockchain.getLastBlock());
        Assert.assertTrue(blockJane.getEnv().blockchain.isCachedChainValid());
    }

}
