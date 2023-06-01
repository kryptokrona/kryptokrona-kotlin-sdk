import kotlinx.coroutines.runBlocking
import org.kryptokrona.sdk.walletapi.client.WalletClient
import org.kryptokrona.sdk.walletapi.model.WalletApi

fun createWalletWithWalletApi() = runBlocking {
    val walletApiConfig = WalletApi("", 11898, "", "", false)
    val walletClient = WalletClient(walletApiConfig)

    walletClient.createWallet()
}