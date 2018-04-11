package com.github.yurivin.blockjane.contract;

import com.github.yurivin.blockjane.block.iBlock;
import com.github.yurivin.blockjane.contracts.ContractLanguage;
import com.github.yurivin.blockjane.contracts.ContractProcessor;
import com.github.yurivin.blockjane.contracts.iContractProcessor;
import com.github.yurivin.blockjane.infrastracture.Environment;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class TestContracts {

    public static final String groovyClassStr = "import com.github.yurivin.blockjane.contracts.iContract\n" +
            "\n" +
            "class TestContract implements iContract {\n" +
            "    @Override\n" +
            "    byte[] execute() {\n" +
            "        return new String(\"Hello!\").getBytes(\"UTF-8\")\n" +
            "    }\n" +
            "}";

    public static final String blockDataWithContract = "{\"contract\":{\"hash\":\"8cb54b149636a9e8e04a6c64d878b9d4f1e72ce09225b9db416029a61036c65e\"," +
            "\"code\":\"import com.github.yurivin.blockjane.contracts.iContract\\n\\nclass TestContract implements iContract {\\n    " +
            "@Override\\n    byte[] execute() {\\n        return new String(\\\"Hello!\\\").getBytes(\\\"UTF-8\\\")\\n    }\\n}\"," +
            "\"language\":\"Groovy\"}}";

    @Test
    public void testLoadAndRun() throws Exception {

        Environment env = new Environment();
        iContractProcessor processor = new ContractProcessor(env);
        byte[] result = processor.loadAndRun(groovyClassStr, ContractLanguage.Groovy);
        Assert.assertEquals("Hello!", new String(result, "UTF-8"));
    }

    @Test
    public void testSaveContractToBlockData() throws Exception {
        Environment env = new Environment();
        iContractProcessor processor = new ContractProcessor(env);
        processor.addContractToBlock(groovyClassStr, ContractLanguage.Groovy);
        env.blockchain.newBlock();
        iBlock block = env.blockchain.newBlock();
        assertNotNull(block);
        assertEquals(blockDataWithContract.intern(), block.getData().toString().intern()); // Test that block contains contract
        assertEquals(block.getData().findValue("hash").asText().intern(), env.hashAlgo.apply(groovyClassStr)); // Test that block data contains contract Hash
        assertEquals(block.getData().findValue("code").asText().intern(), groovyClassStr); //  Test that block data contains contract code
        assertEquals(block.getData().findValue("language").asText().intern(), ContractLanguage.Groovy.title); //  Test that block data contains contract language

    }
}
