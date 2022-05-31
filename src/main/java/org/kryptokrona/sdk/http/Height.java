package org.kryptokrona.sdk.http;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Height.java
 *
 * Represents a model to store information about the
 * height of the daemon and the network.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class Height {

    private int     height;
    private int     networkHeight;
    private String  status;

    public Height(int height, int networkHeight, String status) {
        this.height         = height;
        this.networkHeight  = networkHeight;
        this.status         = status;
    }
}
