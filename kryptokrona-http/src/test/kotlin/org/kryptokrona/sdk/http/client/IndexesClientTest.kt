package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.util.node.Node

class IndexesClientTest {

    private val nodeHTTP = Node("techy.ddns.net", 11898, false)

    private val nodeHTTPS = Node("privacymine.net", 21898, true)

    private val clientHTTP = IndexesClient(nodeHTTP)

    private val clientHTTPS = IndexesClient(nodeHTTPS)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get O Indexes` () = runTest {
        var data = clientHTTP.getOIndexes()
        assertNotNull(data)

        data = clientHTTPS.getOIndexes()
        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get global indexes for range` () = runTest {
        var data = clientHTTP.getGlobalIndexesForRange()
        assertNotNull(data)

        data = clientHTTPS.getGlobalIndexesForRange()
        assertNotNull(data)
    }

}