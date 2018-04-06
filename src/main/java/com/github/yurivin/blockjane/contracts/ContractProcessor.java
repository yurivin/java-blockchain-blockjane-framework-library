package com.github.yurivin.blockjane.contracts;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.yurivin.blockjane.infrastracture.Environment;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContractProcessor implements iContractProcessor {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public ContractProcessor(Environment env) {
        this.env = env;
    }

    Environment env;

    @Override
    public byte[] loadAndRun(String contract){
        log.debug("Contract code to execute: {}", contract);
        final GroovyClassLoader classLoader = new GroovyClassLoader();
        // Load string as Groovy script class.
        Class groovy = classLoader.parseClass(contract);
        GroovyObject groovyObj = getGroovyObject(groovy);
        byte[] result = (byte[])groovyObj.invokeMethod("execute", new Object[]{});
        String contractHash = env.hashAlgo.apply(contract);
        ObjectNode data = env.jsonMapper.createObjectNode();
        data.put(contractHash, result);
        env.blockchain.addBlockData(data.toString());
        return result;
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
