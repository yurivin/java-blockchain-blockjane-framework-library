package com.github.yurivin.blockjane.contracts;

public interface iContractProcessor {

    byte[] loadAndRun(String contract) throws Exception;

}
