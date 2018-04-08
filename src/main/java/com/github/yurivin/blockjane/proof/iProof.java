package com.github.yurivin.blockjane.proof;


import com.github.yurivin.blockjane.infrastracture.Environment;

public interface iProof {
    /**
     * Method should return values which should be
     * @return
     */
    String proof(String blockData);
    void setEnvironment(Environment env);
    /**
     * Get delayFactor.
     * Affects block creation time.
     * See more in SetDelayFactor method comment
     */
    int getDelayFactor();

    /**
     * Set delayFactor.
     * Some thing like difficulty for Pow algorithm.
     * Should affect block hash creation time.
     * For PoS or maybe other proof types it may be just delay value in seconds or milliseconds.
     * @param delayFactor
     */
    void setDelayFactor(int delayFactor);
}
