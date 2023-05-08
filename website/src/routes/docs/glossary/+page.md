---
title: Glossary
---

Here is a collected glossary with some common terminology that you may encounter in this project that is valuable to read more upon to understand the code base and the project.

## Cryptography

### Public/Private Key

Private and public keys are a pair of codes used in cryptography for secure communication. The private key is kept secret and is used to decrypt messages that have been encrypted with the corresponding public key. The public key is shared with others and is used to encrypt messages that can only be decrypted with the corresponding private key.

This system ensures that only the intended recipient can read the message, as only they have access to the private key needed to decrypt it. Public and private keys are used in many different types of secure communication, including email encryption and digital signatures, and are a fundamental part of modern cryptography.


### Public/Private View Key

Public/private view key pair is used to enable a user to see their transaction history without revealing their balance to others. The public view key is shared with others, and it allows them to view the transactions that have been sent to that user's address. The private view key is kept secret by the user and allows them to decrypt the transaction data and view their full transaction history. Essentially, the public view key enables others to verify that the user has received payments without revealing the user's balance, while the private view key enables the user to see their own balance.


### Public/Private Spend Key

Public/private spend key pair is used to enable a user to spend their funds without revealing their identity. The public spend key is used to receive payments from other users, and it allows others to verify that the user has indeed sent funds to their address. The private spend key, on the other hand, is kept secret by the user and allows them to sign transactions and spend their funds. Essentially, the public spend key allows others to verify that the user has sent funds without revealing the user's identity, while the private spend key enables the user to spend their funds without revealing their identity.

### Key Image

A key image is a unique, one-time-use digital representation of a cryptographic key that is used in certain types of cryptocurrencies, such as Kryptokrona.

The primary purpose of a key image is to prevent double-spending in the cryptocurrency network. When a user wants to spend their Kryptokrona coins, they must provide a key image that corresponds to the private key used to generate the transaction. The network checks the key image against a database of previously used key images to ensure that the coins being spent haven't already been used in a previous transaction. This prevents malicious users from spending the same coins more than once.

In addition to preventing double-spending, key images also enhance privacy and anonymity in the Kryptokrona network. By using one-time-use key images, it is difficult for third parties to link transactions to specific users, since each transaction has a unique key image that can't be reused. Overall, key images play an important role in the security and privacy of Kryptokrona transactions.

### Symmetric/Assymetric Cryptography

Symmetric cryptography, also known as secret-key cryptography, is a method of encrypting and decrypting data using a single shared secret key. In this method, both the sender and receiver of a message share the same key, and the sender uses the key to encrypt the message, while the receiver uses the same key to decrypt it. The key must be kept secret to maintain security, and any compromise of the key could compromise the security of the encrypted data. Symmetric cryptography is fast and efficient, but it suffers from the problem of key distribution - the difficulty of securely sharing the key with the intended recipient.

Asymmetric cryptography, also known as public-key cryptography, is a method of encrypting and decrypting data using two separate keys - a public key and a private key. In this method, the sender encrypts the message using the receiver's public key, which can be freely shared, and the receiver uses their private key to decrypt it. Conversely, the sender can digitally sign a message using their private key, which can be verified using their public key. Asymmetric cryptography provides a solution to the problem of key distribution, as the public keys can be shared openly without compromising security. Asymmetric cryptography is slower and more resource-intensive than symmetric cryptography, but it provides better security and key management capabilities.

### Nonce

A nonce is a random or pseudo-random number that is used only once in cryptographic protocols. The purpose of a nonce is to add randomness and uniqueness to a message, preventing replay attacks and ensuring that the same message cannot be used twice. Nonces are often used in combination with other cryptographic primitives such as encryption, digital signatures, and message authentication codes.

### Hash

A hash function is a mathematical function that takes in an input (or message) of arbitrary size and produces a fixed-size output, called a hash or message digest. The output of a hash function is unique to the input, meaning that any small change in the input results in a vastly different output. Hash functions are used in cryptography for a variety of purposes, including password storage, message integrity verification, and digital signatures.

### Key Derivation

Key derivation is the process of generating one or more secret keys from a shared secret, such as a password or a Diffie-Hellman shared secret. The process typically involves a key derivation function (KDF), which takes in the shared secret and produces one or more keys. The purpose of key derivation is to generate a unique key for each use case, ensuring that if one key is compromised, other keys are not affected. Key derivation is often used in conjunction with password-based authentication, where a password is used to derive a secret key for use in symmetric cryptography.


## Blockchain

### Block

A block is a group of transactions that have been validated and added to the blockchain in a blockchain network. Each block contains a header, which includes metadata about the block, such as the block's hash, timestamp, and the hash of the previous block in the blockchain. The transactions in the block are confirmed by miners, who compete to add new blocks to the blockchain by solving a complex mathematical problem. Once a block is added to the blockchain, it cannot be altered or deleted, making the blockchain a secure and tamper-proof ledger.

### Proof of Work (PoW)

Proof of Work (PoW) is a consensus mechanism used in many blockchain networks, including Kryptokrona (XKR). It is a method of ensuring that transactions on the network are valid and that new blocks are added to the blockchain in a secure and decentralized manner. In PoW, miners compete to solve a complex mathematical problem, which requires a significant amount of computational power. The first miner to solve the problem and validate the transaction is rewarded with new cryptocurrency coins. The validation process is called mining, and it requires significant amounts of electricity and computing resources. PoW is designed to be a fair and decentralized method of achieving consensus on a blockchain network.

### Difficulty

Difficulty is a parameter in a blockchain network that adjusts the complexity of the mathematical problem that miners must solve to add a new block to the blockchain. The difficulty is adjusted automatically based on the hashrate of the network to maintain a consistent block time. As the hashrate of the network increases, the difficulty of the problem increases, making it more difficult and time-consuming for miners to solve. The difficulty is an important parameter in maintaining the security and stability of a blockchain network.

### CryptoNight Algorithm

CryptoNight is a hashing algorithm used in several cryptocurrencies, including Kryptokrona. It was designed to be ASIC-resistant, meaning that it is difficult to create specialized hardware that can perform the calculations required to mine coins using this algorithm.

### Hashrate

Hashrate is a measure of the computational power used to secure a blockchain network. It represents the number of hash operations performed by the network's mining nodes in a given time period, usually measured in hashes per second (H/s), kilohashes per second (kH/s), megahashes per second (MH/s), or gigahashes per second (GH/s). The hashrate is an important metric for measuring the security and efficiency of a blockchain network.

### Supply

Supply refers to the total number of coins or tokens that will ever exist in the network. The supply can be fixed or variable, depending on the cryptocurrency's design. For example, Kryptokrona (XKR) has a fixed supply of 1 billion coins, while Monero's supply uses tail emission, to give a percentage to the miners after all coins have been mined to keep up incentives to mine. The supply is an important factor in determining the value of a cryptocurrency, as it affects the scarcity and demand for the asset.

### Outputs

In a cryptocurrency transaction, outputs refer to the destination addresses and amounts that are being sent. Each output specifies the amount of cryptocurrency being sent and the public address of the recipient.

### Transaction

A transaction is a transfer of cryptocurrency between two parties on a blockchain network. It is a record of the transfer, including the source of the funds, the destination of the funds, and the amount of the transfer. Transactions are recorded on the blockchain and are verified and validated by the network's consensus algorithm.

### Coinbase Transaction

A Coinbase transaction is a special type of transaction that is the first transaction in a new block in a blockchain network. It is created by the miner who successfully solves the cryptographic puzzle to add a new block to the blockchain, and it includes a reward in the form of newly created cryptocurrency coins, as well as any transaction fees from the transactions included in the block.

### Raw Coinbase Transaction

A Raw Coinbase Transaction is a hexadecimal representation of the Coinbase transaction, before it has been broadcast to the network. It includes the inputs, outputs, and other metadata of the transaction in a format that can be easily parsed by the network's nodes.

### Local/Remote Nodes

A local node is a node running on the same device as the user, while a remote node is a node running on a different device, typically hosted by a third-party service provider.

### Genesis Block

The Genesis Block is the first block in a blockchain network. It is typically hardcoded into the software and serves as the starting point for the blockchain's transaction history.

### Subaddress

A subaddress is a type of address used in Kryptokrona (XKR) and other privacy-focused cryptocurrencies. It allows users to generate new receiving addresses for each transaction, making it more difficult for others to track their transaction history.

### Stealth Address

A stealth address is another type of address used in Kryptokrona (XKR) and other privacy-focused cryptocurrencies. It allows a sender to generate a unique one-time address for each recipient, making it more difficult for others to trace the recipient's transaction history.

### Ring Signatures

Ring signatures are a type of digital signature used in privacy-focused cryptocurrencies like Kryptokrona (XKR). They allow a user to sign a message using a group of public keys, making it difficult for others to determine which key was actually used to sign the message.

### Mnemonic Seed

A mnemonic seed, also known as a seed phrase, is a series of words that can be used to recover a cryptocurrency wallet. It is a human-readable representation of a cryptographic key, and it allows users to recover their wallet in case of loss or theft of their device. Mnemonic seeds typically consist of 12 to 24 words and are generated using a specific algorithm.

### Block Height

Block height is the number of blocks added to a blockchain since its inception, which serves as a measure of how far the blockchain has progressed and helps to ensure the integrity and security of the blockchain.

### Node

In a decentralized cryptocurrency or blockchain network, a node is a computer or server that independently verifies and validates transactions, contributes to the creation of new blocks, and helps maintain the integrity and accuracy of the blockchain ledger.

### Pool

In cryptocurrency mining, a pool is a group of individual miners who work together to increase their chances of earning rewards. Each miner contributes their computing power to the pool, which then collectively works to solve the complex mathematical problems required to mine new blocks and earn rewards.

### Peer

In a blockchain network, a peer refers to any node that participates in maintaining the network's blockchain. Each peer maintains a copy of the blockchain and communicates with other peers in the network to validate new transactions and add new blocks to the blockchain. Peers can be either full nodes or light nodes, depending on their level of participation in the network.

### Transaction Pool

A transaction pool, also known as a mempool, is a temporary storage area in a blockchain network where unconfirmed transactions are held. When a user initiates a transaction on the network, it is broadcast to all nodes in the network and added to the transaction pool of each node. The transaction remains in the pool until it is validated and added to the blockchain by a miner.

### Fork

In a blockchain network, a fork occurs when the blockchain splits into two or more branches, each following a different set of rules. This can happen for several reasons, such as a change in the consensus rules, a disagreement among the community, or a bug in the software.

There are two types of forks: soft forks and hard forks. Soft forks occur when a change is made to the network's consensus rules that is backward-compatible, meaning that nodes that have not upgraded to the new rules can still validate transactions and participate in the network. Hard forks, on the other hand, occur when a change is made to the consensus rules that is not backward-compatible, meaning that nodes that have not upgraded to the new rules cannot validate transactions or participate in the network.

## Misc

### Remote Procedure Call (RPC)

Remote Procedure Call (RPC) is a technology that allows a computer program to execute a subroutine or procedure on a remote server, as if it were running locally. In an RPC, a client sends a request message to a remote server, which then processes the request and returns a response message. The client program does not need to know how the server processes the request; it only needs to know the parameters of the request and the expected format of the response.