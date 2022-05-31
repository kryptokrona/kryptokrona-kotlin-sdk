package org.kryptokrona.sdk.wallet;

public interface Wallet {

    /**
     *
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
     *
     */
    void stop();
}
