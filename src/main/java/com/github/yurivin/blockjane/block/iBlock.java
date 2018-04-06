package com.github.yurivin.blockjane.block;

import com.github.yurivin.blockjane.infrastracture.Environment;

public interface iBlock {

    String getHash();
    Environment getEnv();
    Long getId();
    iBlock getPreviousBlock();
    String getData();
}
