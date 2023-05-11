import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.kryptokrona.sdk.util.model.node.Node
import org.kryptokrona.sdk.wallet.service.WalletService

fun main() = runBlocking {

    // setting up which node to connect to
    val node = Node("privacymine.net", 11898, false)

    // initializing the wallet service
    val walletService = WalletService(node)

    // setting initial start height to be sync from
    walletService.setStartHeight(1364842L)

    // launching the sync process
    launch {
        while(isActive) {
            walletService.startSync()
        }
    }

    // saving encrypted wallet to file
    walletService.saveWalletToFile("mjovanc.wallet", "someLongPassword")

    // loading the wallet from file, so we can use it to get information
    // this is only needed if we want to obtain information from the wallet
    // not necessary if we just want to populate it with data, which is done by the sync process
    walletService.loadWalletFromFile("mjovanc.wallet", "someLongPassword")

    // getting the wallet we just created
    val wallet = walletService.getWallet()

    // printing out the wallet to get a better understanding of what it looks like
    println(wallet)

    // setting delay so we don't cancel immediately
    // you probably will not use a delay in your code :)
    delay(30000)

    // canceling the sync process
    //job.cancel()
    walletService.stopSync()

    // printing the results
    walletService.getNodeInfo()?.let { println(it) }
    walletService.getStoredBlocks().forEach { println(it) }
}
