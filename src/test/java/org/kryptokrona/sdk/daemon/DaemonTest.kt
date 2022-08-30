package org.kryptokrona.sdk.daemon;

import inet.ipaddr.HostName
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.exception.daemon.DaemonOfflineException
import java.io.IOException
import java.net.UnknownHostException
import kotlin.test.assertFailsWith

class DaemonTest {

    @Test
    fun `initialize daemon`() {
        DaemonImpl(HostName("swepool.org:11898"), false)
    }

    @Test
    fun `initialize daemon with false url should not work`() {
        assertFailsWith<UnknownHostException> {
            DaemonImpl(HostName("mjovanc.kryptokrona.se:11898"), false)
        }
    }

}
