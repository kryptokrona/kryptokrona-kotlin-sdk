package org.kryptokrona.sdk.exception.daemon;

/**
 * DaemonOfflineException.java
 *
 * Could not contact the daemon to complete the request. Ensure it is
 * online and not frozen.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class DaemonOfflineException extends DaemonException {
    public DaemonOfflineException(String errorMessage) {
        super(errorMessage);
    }
}
