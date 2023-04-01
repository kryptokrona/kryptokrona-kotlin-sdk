package org.kryptokrona.sdk.core.wallet

data class TransactionInput(
    val keyImage: String,
    val amount: Long,
    val blockHeight: Long,
    val transactionPublicKey: String,
    val transactionIndex: Long,
    var globalOutputIndex: Long?,
    val key: String,
    var spendHeight: Long,
    val unlockTime: Long,
    var parentTransactionHash: String,
    var privateEphemeral: String?
)