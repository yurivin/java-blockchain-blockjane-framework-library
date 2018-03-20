package com.github.yurivin.blockjane.consensus;


public interface iConsensus {

    String hashPart();
    String generateConsensus();
    void setBlockData(String data);
}
