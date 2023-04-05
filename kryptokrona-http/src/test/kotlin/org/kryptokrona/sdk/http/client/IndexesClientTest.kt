package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.http.model.request.GlobalIndexesForRangeRequest
import org.kryptokrona.sdk.util.node.Node
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class IndexesClientTest {

    private val nodeHTTP = Node("techy.ddns.net", 11898, false)

    private val nodeHTTPS = Node("privacymine.net", 21898, true)

    private val clientHTTP = IndexesClient(nodeHTTP)

    private val clientHTTPS = IndexesClient(nodeHTTPS)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get global indexes for range` () = runTest {
        // Arrange
        val globalIndexesForRangeRequest = GlobalIndexesForRangeRequest(startHeight = 0, endHeight = 1)

        // Act
        val data1 = clientHTTP.getGlobalIndexesForRange(globalIndexesForRangeRequest)
        val data2 = clientHTTPS.getGlobalIndexesForRange(globalIndexesForRangeRequest)

        // Assert
        if (data1 != null) assertEquals(data1.status, "OK")
        if (data2 != null) assertEquals(data2.status, "OK")
    }

}