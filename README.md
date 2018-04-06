# java-blockchain-blockjane-framework-library
BlockJane - Flexible blockchain framework for Java development

### Donate
* LTC - LbcTExZ9EEUNp61dwDnJgYKAKoQq29vhEw
* BTC - 1N6iBNAYiX5ybxdCWTJ9LMZDuQcXDQCLu9
* ETH - 0x0d8a2920ccdb08959f2df1f939db5accd2558dfe
* XEM - NBKENJ-Q5IS6C-NZEO6F-T5BM5S-K2IZZI-D5UCHJ-K3SF
* NEO - ARLBymMsUDYyqe7QEpxsJGYCKTeZZ8UiQP
## Whitelist of BlockJane
BlocJane ia a Java Blockchain library created for flexible and fast blockchain development for enterprise needs.

## Features
1. To create a chain of blocks and add a data in every block. Use it as a storage that provides data integrity and historical links to previous blocks.
2. Validate that data in blockchain is valid and every block is the same as it was created. If one block will be chainged, next blcok will show hash desintegration. Functionality provided by
3. Create Identities with privateKey and publicKey. May be used like wallets or KYC. 
4. Signature.
5. MultiHash.
6. SmartContracts execution. Smart contract data saving to block. Saving Smart contracts code to block and loading from there is in progress.

## TODO list
1. Make that when we change some blockchain properties Blockchain save info about nes state and time block of changing state. Also save initial state in blockchain. We need this to make possible validation of all blockchain after chaging properties like proofType, hashing algo, nonce and others.
2. Realize api to add data to new block
3. Write test for invalid chain hashes. When isChainValid method will give error of chain validation.
4. Add transaction history to the wallet
5. P2p node connection 
6. Node discovery functionality. Simplest is by having 'ip:port' in config file. & Get from config server. & Get from other nodes. & Get from Torrent like network
7. Multi node proofType functionality
8. Smart contracts for JVM languages. Groovy & Kotlin & Scala $ Jyton & Jruby
9. Smart contracts fo javascript. Is it also exists on JVM?
10. At list one database serializer


## Links

### Beginners blockchain
1. Blockchain tutorial part 1: https://github.com/CryptoKass/NoobChain-Tutorial-Part-1
2. Blockchain tutorial part 2: https://github.com/CryptoKass/NoobChain-Tutorial-Part-2
3. List of other awesome blockchain tutorials: https://github.com/openblockchains/awesome-blockchains
4. Papers on blockchain (found by rjc): https://github.com/bellaj/Blockchain
5. Crypto wallet example: https://github.com/Levalicious/CryptoWallet
6. Peer to Peer sources: https://github.com/kgryte/awesome-peer-to-peer
7. Simple Java Blockchain: https://github.com/dottorm/SimpleBlockChain
8. Java Blockchain Example: https://github.com/TechPrimers/java-blockchain-example
9. Blockchain chaincode for Java developers from IBM: https://www.ibm.com/developerworks/library/j-chaincode-for-java-developers/j-chaincode-for-java-developers-pdf.pdf
10. SimpleBlockchain C#: https://github.com/thabart/SimpleBlockChain 

### Smart contracts
1. Hello world Smart contract (Solidity tutorial): https://www.ethereum.org/greeter
2. Begginers guide - smart contracts (solidity video tutorial): https://www.youtube.com/watch?v=R_CiemcFKis
3. Neo Smart Contracts (C# tutorial): https://www.linkedin.com/pulse/neo-smart-contract-development-m%C3%A1t%C3%A9-moln%C3%A1r

### Production tools
Using the right tools : 
1. Coinbase API (useful for developing apps for bitcoin/ltc/eth): https://developers.coinbase.com/
2. Remix - Browser based solidity editor (write and test ethereum smart contracts): https://remix.ethereum.org/
3. BitcoinJ Java source code (have a browse through the bitcoinj repo): https://github.com/bitcoin-labs/bitcoinj-minimal/tree/master/core
4. HyperLedger fabric (permissioned blockchain framework): https://hyperledger.org/projects/fabric
5. HyperLedger sawtooth (blockchain with smart contract support): https://sawtooth.hyperledger.org/docs/core/releases/latest/introduction.html
6. Bouncey castle cryptographic library (Java library): https://www.bouncycastle.org/latest_releases.html


