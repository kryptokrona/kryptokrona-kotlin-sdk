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

    // setting delay so we don't cancel immediately
    // you probably will not use a delay in your code :)
    delay(60 * 1000)

    // canceling the sync process
    job.cancel()

    // printing the results
    walletService.getNodeInfo()?.let { println(it) }
    walletService.getStoredBlocks().forEach { println(it) }
}
