package com.github.yurivin.blockjane;

import com.github.yurivin.blockjane.blockchain.DummyBlockchain;
import com.github.yurivin.blockjane.hash.algorithm.SHA256;
import com.github.yurivin.blockjane.hash.algorithm.iAlgo;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.serializers.DummyCollectionSerializer;
import org.junit.Test;

public class TestBlockchain {

    @Test
    public void mainTest() {
        Environment env = new Environment(new SHA256(), new DummyBlockchain(), new DummyCollectionSerializer(10));
        BlockJane blockJane = new BlockJane(env);

    }
}
