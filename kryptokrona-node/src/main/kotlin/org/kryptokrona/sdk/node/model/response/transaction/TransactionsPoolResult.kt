package org.kryptokrona.sdk.node.model.response.transaction

import kotlinx.serialization.Serializable

@Serializable
data class TransactionsPoolResult(
    val status: String,
    val transactions: List<TransactionInfo>
)
