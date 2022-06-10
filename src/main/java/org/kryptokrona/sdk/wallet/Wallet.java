package org.kryptokrona.sdk.wallet;

public interface Wallet {

    /**
     * Initializes and starts the wallet sync process. You should call this
     * method before enquiring about daemon info or fee info.
     * The wallet will not process blocks until you call this method.
     */
    void start();

    /**
     * Saves a wallet to a file.
     *
     * @param fileName : String
     * @param password : String
     */
    void saveToFile(String fileName, String password);

    /**
     * The inverse of the start method, this pauses the blockchain sync process.
     *
     * If you want the node process to close cleanly, you need to call this method.
     * Otherwise, the library will keep firing callbacks and so your script will hang.
     */
    void stop();
}
