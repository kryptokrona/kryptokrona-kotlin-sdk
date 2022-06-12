package org.kryptokrona.sdk.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MixinLimits.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class MixinLimits {

    private List<MixinLimit> limits;
    private int defaultMixin;

    public MixinLimits(List<MixinLimit> limits, int defaultMixin) {
        this.limits = limits.stream()
                .sorted(Comparator.comparing(MixinLimit::getHeight).reversed())
                .collect(Collectors.toList());
        this.defaultMixin = defaultMixin;
    }
}
