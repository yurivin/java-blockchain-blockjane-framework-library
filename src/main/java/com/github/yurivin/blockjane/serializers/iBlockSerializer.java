package com.github.yurivin.blockjane.serializers;

import com.github.yurivin.blockjane.block.Block;
import com.github.yurivin.blockjane.block.iBlock;

import java.util.List;

public interface iBlockSerializer {

    /**
     * Returns true if block was serialized.
     * Return false if block was not successfully serialized.
     * @param block
     * @return
     */
    boolean serialize(iBlock block);

    /**
     * Method to check serialized chain validity.
     * @return
     */
    Boolean isSerializedChainValid();

    /**
     * Method to check chain validity.
     * May be used for serialized and cached chains.
     * @param blocks
     * @return
     */
    Boolean isChainValid(List<iBlock> blocks);
}
