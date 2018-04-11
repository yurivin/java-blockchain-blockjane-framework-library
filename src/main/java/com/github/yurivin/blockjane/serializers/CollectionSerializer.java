package com.github.yurivin.blockjane.serializers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.yurivin.blockjane.block.iBlock;
import com.github.yurivin.blockjane.exception.InvalidBlockchainStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionSerializer implements iSerializer, iDeserializer {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final List<iBlock> serializedBlocks;
    private final Map<String, Long> contractPointers = new HashMap<>();
    private int maxSize;

    public CollectionSerializer(int maxSize) {
        this.maxSize = maxSize;
        this.serializedBlocks = new ArrayList<>(maxSize);
    }

    @Override
    public boolean serialize(iBlock block) {
        try {
            if (serializedBlocks.size() > maxSize) {
                throw new InvalidBlockchainStateException("Riches max size of blockchain: " + maxSize);
            }
            serializedBlocks.add(block);
            return true;
        } catch (Exception e) {
            log.error("Error during serializing blocks", e);
            return false;
        }
    }

    @Override
    public Long serializeContractPointer(String contractHash, Long blockId) {
        return contractPointers.put(contractHash, blockId);
    }

    @Override
    public Boolean isSerializedChainValid() {

        return isChainValid(serializedBlocks);
    }

    public Boolean isChainValid(List<iBlock> blocks) {
        //loop through blockchain to check hashes:
        for (int i = 1; i < blocks.size(); i++) {
            iBlock currentBlock = blocks.get(i);
            //compare registered hash and calculated hash:
            if (!currentBlock.getHash().equals(currentBlock.getEnv().proofType.proof(currentBlock.getData().toString()))) {
                log.error("Current Hashes not equal for block id: " + currentBlock.getHash());
                return false;
            }
        }
        log.info("Validating blockchain: Chain is valid");
        return true;
    }

    /**
     * Method for testing purposes
     *
     * @return
     */
    public List<iBlock> getSerializedBlocks() {
        return serializedBlocks;
    }

    @Override
    public iBlock getBLock(String blockHash) {
        /** Fix contains to right way equals */
        serializedBlocks.stream().filter(block -> block.getHash().contains(blockHash)).findFirst().get();
        return serializedBlocks.stream().filter(block -> block.getHash().contains(blockHash)).findFirst().get();
    }

    @Override
    public ObjectNode getContract(String contractHash) {
        iBlock contractBlock = serializedBlocks.stream().filter(block -> block.getId().equals(contractPointers.get(contractHash))).findFirst().get();
        return (ObjectNode) contractBlock.getData().findValue("contract");
    }
}
