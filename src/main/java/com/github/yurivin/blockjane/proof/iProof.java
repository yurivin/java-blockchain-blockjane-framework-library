package com.github.yurivin.blockjane.proof;


import com.github.yurivin.blockjane.infrastracture.Environment;

public interface iProof {
    /**
     * Method should return values which should be the same for the same data in block
     * @return
     */
    String proof();
    void setBlockData(String data);
    void setEnvironment(Environment env);
}
