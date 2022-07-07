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
    public DaemonOfflineException() {
        super(
                "We were not able to submit our request to the daemon. " +
                "Ensure it is online and not frozen."
        );
    }
}
