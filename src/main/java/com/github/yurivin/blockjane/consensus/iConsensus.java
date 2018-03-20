package com.github.yurivin.blockjane.consensus;


import com.github.yurivin.blockjane.infrastracture.Environment;

public interface iConsensus {
    /**
     * Method should return values which should be
     * @return
     */
    String generateConsensus();
    void setBlockData(String data);
    void setEnvironment(Environment env);
}
