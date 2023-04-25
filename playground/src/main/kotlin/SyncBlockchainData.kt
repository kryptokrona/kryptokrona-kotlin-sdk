import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.kryptokrona.sdk.core.service.WalletService
import org.kryptokrona.sdk.util.model.node.Node

fun main() = runBlocking {

    // setting up which node to connect to
    val node = Node("privacymine.net", 11898, false)

    // initializing the wallet service
    val walletService = WalletService(node)

    // setting initial start height to be sync from
    walletService.setStartHeight(1364842L)

    // launching the sync process
    val job = launch {
        while(isActive) {
            walletService.startSync()
        }
    }

    // setting delay so we don't cancel immediately
    // you probably will not use a delay in your code :)
    delay(60 * 10000)

    // canceling the sync process
    job.cancel()

    // printing the results
    walletService.getNodeInfo()?.let { println(it) }
    walletService.getStoredBlocks().forEach { println(it) }
}
