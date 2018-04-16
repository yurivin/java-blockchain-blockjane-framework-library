package com.github.yurivin.blockjane.contracts;

public interface iContractProcessor {

    byte[] loadAndRun(String contract, ContractLanguage language);
    void addContractToBlock(String contract, ContractLanguage language);

}
