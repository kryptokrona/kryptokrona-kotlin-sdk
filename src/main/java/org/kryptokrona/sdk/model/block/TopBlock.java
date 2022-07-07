package org.kryptokrona.sdk.model.block;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * TopBlock.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class TopBlock {

    private String  hash;
    private long    height;

    public TopBlock(String hash, long height) {
        this.hash = hash;
        this.height = height;
    }
}
