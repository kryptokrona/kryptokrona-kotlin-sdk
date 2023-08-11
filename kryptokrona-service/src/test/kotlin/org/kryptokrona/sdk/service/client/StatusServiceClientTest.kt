package org.kryptokrona.sdk.service.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.service.model.Service

class StatusServiceClientTest {

    private val service = Service("localhost", 8070, "false", "", false)

    private val client = StatusServiceClient(service)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get status`() = runTest {

    }
}