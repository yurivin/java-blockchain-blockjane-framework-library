package com.github.yurivin.blockjane.contracts;

import com.github.yurivin.blockjane.infrastracture.Environment;

public interface iContractProcessor {

    byte[] loadAndRun(String contract, ContractLanguage language);
    void addContractToBlock(String contract, ContractLanguage language);

}
