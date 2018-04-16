package com.github.yurivin.blockjane.p2p;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.yurivin.blockjane.block.iBlock;

import java.net.InetAddress;
import java.util.List;

/**
 * This class contains p2p or network methods.
 * Return value of methods provides what should be sended.
 * And parameters of methods provides what blockchain components would like to send to the network.
 */
public interface iP2pRequest {

    /**
     * @param fromBlockId - oldest block by ID.
     * @param count - count of blocks to return.
     * @return list of blocks added to chain.
     */
    List<iBlock> getChainHistory(Long fromBlockId, Integer count);

    /**
     * Get block by Id or by hash
     * @param blockId
     * @param blockHash
     * @return
     */
    iBlock getBlock(Long blockId, String blockHash);

    /**
     * @param data - blockchain data json object
     * @return
     */
    ObjectNode sendData(ObjectNode data);

    /**
     * @param field - data field name
     * @param hash - hash provided in data object
     * @return data json object
     */
    ObjectNode getData(String field, String hash);

    /**
     * @param data - send data candidate to include in a one of next blocks
     * @return data candidate to include in a new block
     */
    ObjectNode sendDataCandidate(ObjectNode data);

    /**
     * Request for active network blockchain nodes
     * @return array of addresses
     */
    void getNodeAddresses();

    /**
     * Ping node to ensure it is up and running.
     * @param nodeAddress
     * @return
     */
    InetAddress pingAddress(String nodeAddress);

}
