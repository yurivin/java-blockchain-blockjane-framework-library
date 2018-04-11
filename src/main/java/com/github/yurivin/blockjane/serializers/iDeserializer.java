package com.github.yurivin.blockjane.serializers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.yurivin.blockjane.block.iBlock;

public interface iDeserializer {


    iBlock getBLock(String blockHash);

    ObjectNode getContract(String contractHash);

}
