import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.kryptokrona.sdk.core.service.WalletService
import org.kryptokrona.sdk.util.model.node.Node

fun main() = runBlocking {
    val node = Node("privacymine.net", 11898, false)
    val walletService = WalletService(node)
    walletService.setStartHeight(1364842L)

    // setting initial start height to be sync from
    // walletService.setStartHeight(10000)

    // launching the sync process
    val job = launch {
        while(isActive) {
            walletService.startSync()
        }
    }

    delay(60 * 1000)
    job.cancel()

    walletService.getNodeInfo()?.let { println(it) }
    walletService.getStoredBlocks().forEach { println(it) }

    println("Hello, world!")
}