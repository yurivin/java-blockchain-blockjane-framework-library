package com.github.yurivin.blockjane.serializers;

import com.github.yurivin.blockjane.infrastracture.Block;

public interface iBlockSerializer {

    /**
     * Returns true if block was serialized.
     * Return false if block was not successfully serialized.
     * @param block
     * @return
     */
    boolean serialize(Block block);
}
