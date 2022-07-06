package org.kryptokrona.sdk.exception.daemon;

/**
 * DaemonErrorException.java
 *
 * An error occured whilst the daemon processed the request. Possibly our
 * software is outdated, the daemon is faulty, or there is a programmer
 * error. Check your daemon logs for more info (set_log 4).
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class DaemonErrorException extends DaemonException {
    public DaemonErrorException(String errorMessage) {
        super(errorMessage);
    }
}
