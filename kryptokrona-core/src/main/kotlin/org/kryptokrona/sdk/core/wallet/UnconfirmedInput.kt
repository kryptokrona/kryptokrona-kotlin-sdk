package org.kryptokrona.sdk.core.wallet

data class UnconfirmedInput(
    val amount: Long,
    val key: String,
    val parentTransactionHash: String
)
