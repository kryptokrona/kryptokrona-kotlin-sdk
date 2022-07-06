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
    public DaemonStillProcessingException(String errorMessage) {
        super(errorMessage);
    }
}
