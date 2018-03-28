package com.github.yurivin.blockjane.transaction.batchhash;

import java.util.List;

public interface iBatchHash {

    String apply(List<String> hashes);
}
