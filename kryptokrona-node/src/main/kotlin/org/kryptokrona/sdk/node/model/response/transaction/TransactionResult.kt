package org.kryptokrona.sdk.node.model.response.transaction

import kotlinx.serialization.Serializable
import org.kryptokrona.sdk.node.model.response.block.BlockInfo

@Serializable
data class TransactionResult(
    val block: BlockInfo,
    val status: String,
    val tx: TransactionInfo,
    val txDetails: TransactionDetails
)
