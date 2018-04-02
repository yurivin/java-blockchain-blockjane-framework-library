package com.github.yurivin.blockjane;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.yurivin.blockjane.block.Block;
import com.github.yurivin.blockjane.block.GenesisBlock;
import com.github.yurivin.blockjane.block.iBlock;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.transaction.iTransaction;
import com.github.yurivin.blockjane.transaction.iTransactionOutput;
import com.github.yurivin.blockjane.transaction.inoutmodel.InOutTransaction;
import com.github.yurivin.blockjane.transaction.inoutmodel.TransactionOutput;
import com.github.yurivin.blockjane.wallet.PublicKeyWallet;
import com.github.yurivin.blockjane.wallet.iWallet;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

public class TestTransactions {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    //@Ignore
    public void test() throws InterruptedException, JsonProcessingException {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); //Setup Bouncey castle as a Security Provider
        Environment env = new Environment();
        BlockJane blockJane = new BlockJane(env);
        //Create wallets:
        iWallet walletA = new PublicKeyWallet(env);
        iWallet walletB = new PublicKeyWallet(env);
        iWallet coinbase = new PublicKeyWallet(env);

        Map<String,iTransactionOutput> UTXOs = new HashMap<>();

        //create genesis transaction, which sends 100 NoobCoin to walletA:
        iTransaction genesisTransaction = new InOutTransaction(coinbase, walletA.getPublicKey(), new BigDecimal("100"), null, env);
        genesisTransaction.getOutputs().add(new TransactionOutput(genesisTransaction.getRecipient(), genesisTransaction.getAmount(), genesisTransaction.getTransactionId(), env)); //manually add the Transactions Output

        blockJane.addTransaction(genesisTransaction);
        env.blockchain.getTransactionOutputs().put(genesisTransaction.getOutputs().get(0).getId(), genesisTransaction.getOutputs().get(0)); //its important to store our first transaction in the UTXOs list.

        blockJane.run();
        Thread.sleep(200);
        blockJane.addTransaction(walletA.sendFunds(walletB.getPublicKey(), new BigDecimal("40")));
        Thread.sleep(200);
        blockJane.setRunning(false);


        /*Block block2 = new Block(block1.hash);
        System.out.println("\nWalletA Attempting to send more funds (1000) than it has...");
        block2.addTransaction(walletA.sendFunds(walletB.publicKey, 1000f));
        addBlock(block2);
        System.out.println("\nWalletA's balance is: " + walletA.getBalance());
        System.out.println("WalletB's balance is: " + walletB.getBalance());

        Block block3 = new Block(block2.hash);
        System.out.println("\nWalletB is Attempting to send funds (20) to WalletA...");
        block3.addTransaction(walletB.sendFunds( walletA.publicKey, 20));
        System.out.println("\nWalletA's balance is: " + walletA.getBalance());
        System.out.println("WalletB's balance is: " + walletB.getBalance());*/

        /*------------------------------------*/

        blockJane.setRunning(false);
        System.out.println("\nWalletA's balance is: " + walletA.getBalance());
        System.out.println("WalletB's balance is: " + walletB.getBalance());

    }

}
