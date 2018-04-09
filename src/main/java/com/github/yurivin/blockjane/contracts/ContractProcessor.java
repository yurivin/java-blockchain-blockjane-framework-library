package com.github.yurivin.blockjane.contracts;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.utils.Entry;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ContractProcessor implements iContractProcessor {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public ContractProcessor(Environment env) {
        this.env = env;
    }

    Environment env;

    @Override
    public byte[] loadAndRun(String contract, ContractLanguage language){
        log.debug("Contract code to execute: {}", contract);
        byte[] result = null;
        switch (language) {
            case Groovy:
                result = runGroovyContract(contract);
                break;
            case Kotlin:
                break;
        }
        String contractHash = env.hashAlgo.apply(contract);
        Map.Entry<String, JsonNode> entry = new Entry(contractHash, env.jsonMapper.convertValue(result, JsonNode.class));
        env.blockchain.addBlockData(entry);
        return result;
    }

    private byte[] runGroovyContract(String contract) {
        final GroovyClassLoader classLoader = new GroovyClassLoader();
        // Load string as Groovy script class.
        Class groovy = classLoader.parseClass(contract);
        GroovyObject groovyObj = getGroovyObject(groovy);
        return (byte[])groovyObj.invokeMethod("execute", new Object[]{});
    }

    /**
     * Adds contract code to block dat as Json field
     * @param contract - contract code to execute;
     * @param language - language in which contract is written.
     * @return
     */
    @Override
    public void addContractToBlock(String contract, ContractLanguage language) {
        ObjectNode value = env.jsonMapper.createObjectNode();
        value.set("hash", env.jsonMapper.convertValue(env.hashAlgo.apply(contract), JsonNode.class));
        value.set("code", env.jsonMapper.convertValue(contract, JsonNode.class));
        value.set("language", env.jsonMapper.convertValue(language.title, JsonNode.class));
        Map.Entry<String, JsonNode> data = new Entry("contract", value);
        env.blockchain.addBlockData(data);
    }

    private GroovyObject getGroovyObject(Class groovy) {
        GroovyObject groovyObj = null;
        try {
            groovyObj = (GroovyObject) groovy.newInstance();
        } catch (Exception e) {
            log.error("Error instantiating Groovy object during contract execution", e);
        }
        return groovyObj;
    }
}
