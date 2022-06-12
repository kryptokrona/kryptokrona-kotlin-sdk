package org.kryptokrona.sdk.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * MixinLimit.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class MixinLimit {

    private int height;
    private int minMixin;
    private int maxMixin;
    private int defaultMixin;

    public MixinLimit(int height, int minMixin, int maxMixin, int defaultMixin) {
        this.height = height;
        this.minMixin = minMixin;
        this.maxMixin = maxMixin;
        this.defaultMixin = defaultMixin;
    }

    public MixinLimit(int height, int minMixin) {
        this.height = height;
        this.minMixin = minMixin;
        this.maxMixin = minMixin;
        this.defaultMixin = minMixin;
    }
}
