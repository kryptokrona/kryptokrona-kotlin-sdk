import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.kryptokrona.sdk.core.service.WalletService
import org.kryptokrona.sdk.util.node.Node

fun main() = runBlocking {
    val node = Node("techy.ddns.net", 11898, false)
    val walletService = WalletService(node)

    // setting initial start height to be sync from
    // walletService.setStartHeight(10000)

    // launching the sync process
    val job = launch {
        while(isActive) {
            walletService.startSync()
        }
    }

    delay(5000)
    job.cancel()

    walletService.getWalletSyncData()?.let { println(it) }
    walletService.getNodeInfo()?.let { println(it) }

    println("Hello, world!")
}