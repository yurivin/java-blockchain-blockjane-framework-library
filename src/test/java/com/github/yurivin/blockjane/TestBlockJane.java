package com.github.yurivin.blockjane;

import com.github.yurivin.blockjane.infrastracture.Environment;
import org.junit.Test;

public class TestBlockJane {

    @Test
    public void mainTest() {
        Environment env = new Environment();
        BlockJane blockJane = new BlockJane(env);
        blockJane.run();
        blockJane.setRunning(false);
    }
}
