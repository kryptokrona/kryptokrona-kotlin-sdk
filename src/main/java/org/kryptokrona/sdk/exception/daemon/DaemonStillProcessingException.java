package org.kryptokrona.sdk.exception.daemon;

/**
 * DaemonStillProcessingException.java
 *
 * The daemon received our request but we timed out before we could figure
 * out if it completed.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class DaemonStillProcessingException extends DaemonException {
    public DaemonStillProcessingException() {
        super(
                "The transaction was sent to the daemon, but the connection " +
                "timed out before we could determine if the transaction " +
                "succeeded. Wait a few minutes before retrying the transaction, " +
                "as it may still succeed."
        );
    }
}
