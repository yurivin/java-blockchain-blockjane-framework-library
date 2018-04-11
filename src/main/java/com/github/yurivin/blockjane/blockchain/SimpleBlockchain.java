package com.github.yurivin.blockjane.blockchain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.yurivin.blockjane.block.Block;
import com.github.yurivin.blockjane.block.GenesisBlock;
import com.github.yurivin.blockjane.block.iBlock;
import com.github.yurivin.blockjane.infrastracture.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SimpleBlockchain implements iBlockchain {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * This property should be not accessible out from this class.
     * It will cause blockchain integrity violation because blocks hash
     * inheritance will be broken.
     */
    private final List<iBlock> blocksCache;
    public Environment env;
    /**
     * This property should be not accessible out from this class.
     * It will cause blockchain integrity violation because blocks hash
     * inheritance will be broken.
     */
    private iBlock lastBlock;
    /**
     * Queue of data to add in to new blocks;
     */
    private Queue<Map.Entry<String, JsonNode>> blockDataQueue = new ConcurrentLinkedQueue<>();


    public SimpleBlockchain() {
        this.blocksCache = new ArrayList<>();
    }


    @Override
    public iBlock newBlock() throws JsonProcessingException {
        iBlock newBlock = null;
        if (lastBlock == null) {
            ObjectNode data = env.jsonMapper.createObjectNode();
            data.set("genesisData",env.jsonMapper.convertValue("Test block data", JsonNode.class));
            newBlock = new GenesisBlock(env, data);
            log.info("Genesis block created: " + mapper.writeValueAsString(newBlock));
        } else {
            ObjectNode mainNode = pollBlockDataQueue();
            newBlock = new Block(mainNode, lastBlock, env);
            log.info("New block created: " + mapper.writeValueAsString(newBlock));
        }
        lastBlock = newBlock;
        blocksCache.add(newBlock);

        return serialize(newBlock) ? newBlock : null;
    }

    private ObjectNode pollBlockDataQueue() {
        Map.Entry<String, JsonNode> dataEntry = blockDataQueue.poll();
        ObjectNode mainNode = null;
        if(dataEntry != null) {
            mainNode = env.jsonMapper.createObjectNode();
            mainNode.set(dataEntry.getKey(), dataEntry.getValue());
        }
        return mainNode;
    }

    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
    }

    private boolean serialize(iBlock newBlock) {
        boolean serialized = false;
        List<iBlock> toRemoveList = new ArrayList<>();
        if (blocksCache.size() > 0) {
            for (iBlock block : blocksCache) {
                serialized = env.serializer.serialize(newBlock);
                if (!serialized) {
                    break;
                } else {
                    toRemoveList.add(block);
                }
            }
            blocksCache.removeAll(toRemoveList);
        }
        return serialized;
    }

    @Override
    public iBlock getLastBlock() {
        return lastBlock;
    }

    /**
     * Method to check chain integrity.
     *
     * @return
     */
    @Override
    public Boolean isCachedChainValid() {
        return env.serializer.isChainValid(blocksCache);
    }

    @Override
    public void addBlockData(Map.Entry<String, JsonNode> data) {
        blockDataQueue.add(data);
    }

}
