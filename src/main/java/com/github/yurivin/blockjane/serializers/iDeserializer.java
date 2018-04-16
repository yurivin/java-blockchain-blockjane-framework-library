package com.github.yurivin.blockjane.serializers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.yurivin.blockjane.block.iBlock;
import com.github.yurivin.blockjane.contracts.Contract;

public interface iDeserializer {


    iBlock getBLock(String blockHash);

    Contract getContract(String contractHash);

    /**
     * Method to scan blocks chain in storage for contracts.
     * Adds contracts hashes to contract pointers collection.
     * @param startBlockId - blocks to start scanning from.
     * @return last sacanned block Id
     */
    Long scanChainForContracts(Long startBlockId);

}
