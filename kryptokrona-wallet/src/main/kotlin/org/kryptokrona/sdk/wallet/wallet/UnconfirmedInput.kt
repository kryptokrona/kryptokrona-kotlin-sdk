package org.kryptokrona.sdk.wallet.wallet

data class UnconfirmedInput(
    val amount: Long,
    val key: String,
    val parentTransactionHash: String
)
