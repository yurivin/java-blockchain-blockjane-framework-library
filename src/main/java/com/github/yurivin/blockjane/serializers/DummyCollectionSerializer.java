package com.github.yurivin.blockjane.serializers;

import com.github.yurivin.blockjane.infrastracture.Block;
import com.github.yurivin.blockjane.serializers.iBlockSerializer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;

@Data
@Slf4j
public class DummyCollectionSerializer implements iBlockSerializer {

    private final ArrayBlockingQueue<Block> serializedBlocks;
    public DummyCollectionSerializer(int maxSize) {
        this.serializedBlocks = new ArrayBlockingQueue<>(maxSize);
    }

    @Override
    public boolean serialize(Block block) {
        try {
            serializedBlocks.add(block);
            return true;
        } catch(Exception e) {
            log.error("Error during serializing blocks", e);
            return false;
        }
    }
}
