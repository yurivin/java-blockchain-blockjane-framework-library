package com.github.yurivin.blockjane.contracts;

public class Contract {

    private String hash;
    private String code;
    private ContractLanguage language;

    public Contract(String hash, String code, ContractLanguage language) {
        this.hash = hash;
        this.code = code;
        this.language = language;
    }

    public String getHash() {
        return hash;
    }

    public String getCode() {
        return code;
    }

    public ContractLanguage getLanguage() {
        return language;
    }
}
