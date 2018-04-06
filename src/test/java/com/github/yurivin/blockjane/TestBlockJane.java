package com.github.yurivin.blockjane;

import com.github.yurivin.blockjane.identity.PublicKeyIdentity;
import com.github.yurivin.blockjane.identity.iIdentity;
import com.github.yurivin.blockjane.infrastracture.Environment;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

public class TestBlockJane {

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    @Test
    public void mainTest() throws InterruptedException {
        //Test Blockchain run
        Environment env = new Environment();
        BlockJane blockJane = new BlockJane(env);
        blockJane.run();
        //Test add data to the block
        blockJane.addBlockData("Test block data");
        blockJane.addBlockData("Other test block data");
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
