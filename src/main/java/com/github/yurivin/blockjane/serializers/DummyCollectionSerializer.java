package com.github.yurivin.blockjane.serializers;

import com.github.yurivin.blockjane.block.iBlock;
import com.github.yurivin.blockjane.exception.InvalidBlockchainStateException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.activity.InvalidActivityException;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class DummyCollectionSerializer implements iBlockSerializer {

    private final List<iBlock> serializedBlocks;
    private int maxSize;
    public DummyCollectionSerializer(int maxSize) {
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
            if(!currentBlock.getHash().equals(currentBlock.calculateHash()) ){
                log.error("Current Hashes not equal for block id: " + currentBlock.getHash());
                return false;
            }
        }
        return true;
    }

}
