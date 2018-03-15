package com.github.yurivin.blockjane.block;

import com.github.yurivin.blockjane.infrastracture.Environment;

public interface iBlock {

    public String calculateHash();

    String getHash();
    Environment getEnv();
    Long getId();
}
