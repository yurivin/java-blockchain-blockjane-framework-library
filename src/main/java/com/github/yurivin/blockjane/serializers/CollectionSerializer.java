package com.github.yurivin.blockjane.serializers;

import com.github.yurivin.blockjane.block.iBlock;
import com.github.yurivin.blockjane.exception.InvalidBlockchainStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CollectionSerializer implements iBlockSerializer {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final List<iBlock> serializedBlocks;
    private int maxSize;
    public CollectionSerializer(int maxSize) {
        this.maxSize = maxSize;
        this.serializedBlocks = new ArrayList<>(maxSize);
    }

    @Override
    public boolean serialize(iBlock block) {
        try {
            if(serializedBlocks.size() > maxSize) {
                throw new InvalidBlockchainStateException("Riched max size of blockchain: " + maxSize);
            }
            serializedBlocks.add(block);
            return true;
        } catch(Exception e) {
            log.error("Error during serializing blocks", e);
            return false;
        }
    }

    @Override
    public Boolean isSerializedChainValid() {

        return isChainValid(serializedBlocks);
    }

    public Boolean isChainValid(List<iBlock> blocks) {
        //loop through blockchain to check hashes:
        for(int i=1; i < blocks.size(); i++) {
            iBlock currentBlock = blocks.get(i);
            //compare registered hash and calculated hash:
            if(!currentBlock.getHash().equals(currentBlock.getEnv().proofType.proof()) ){
                log.error("Current Hashes not equal for block id: " + currentBlock.getHash());
                return false;
            }
        }
        log.info("Validating blockchain: Chain is valid");
        return true;
    }

    /**
     * Method for testing purposes
     * @return
     */
    public List<iBlock> getSerializedBlocks() {
        return serializedBlocks;
    }
}
