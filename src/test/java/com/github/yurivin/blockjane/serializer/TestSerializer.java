package com.github.yurivin.blockjane.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.yurivin.blockjane.block.iBlock;
import com.github.yurivin.blockjane.contracts.ContractLanguage;
import com.github.yurivin.blockjane.contracts.ContractProcessor;
import com.github.yurivin.blockjane.contracts.iContractProcessor;
import com.github.yurivin.blockjane.infrastracture.Environment;
import org.junit.Test;

import static com.github.yurivin.blockjane.contract.TestContracts.groovyClassStr;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class TestSerializer {

    @Test
    public void testGetBlockAndContractPointer() throws JsonProcessingException {
        Environment env = new Environment();
        iContractProcessor processor = new ContractProcessor(env);
        processor.addContractToBlock(groovyClassStr, ContractLanguage.Groovy);
        env.blockchain.newBlock();
        iBlock block = env.blockchain.newBlock();
        assertNotNull(block);
        iBlock block2 = env.deserializer.getBLock(block.getHash());
        assertEquals(block2, block);
        String contractHash = block.getData().findValue("hash").asText();
        env.serializer.serializeContractPointer(contractHash, block.getId());
        ObjectNode contract = env.deserializer.getContract(contractHash);
        assertEquals(contract.findValue("hash").asText().intern(), env.hashAlgo.apply(groovyClassStr)); // Test that block data contains contract Hash
        assertEquals(contract.findValue("code").asText().intern(), groovyClassStr); //  Test that block data contains contract code
        assertEquals(contract.findValue("language").asText().intern(), ContractLanguage.Groovy.title); //  Test that block data contains contract language

    }
}
