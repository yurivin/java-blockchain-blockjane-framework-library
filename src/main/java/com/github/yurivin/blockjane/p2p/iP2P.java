package com.github.yurivin.blockjane.p2p;

import com.github.yurivin.blockjane.block.iBlock;

import java.util.List;

public interface iP2P {

    /**
     * @param fromBlockId - oldest block by ID.
     * @param count - count of blocks to return.
     * @return list of blocks added to chain.
     */
    List<iBlock> getChainHistory(Long fromBlockId, Integer count);

}
