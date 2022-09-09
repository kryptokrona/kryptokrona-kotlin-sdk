package org.kryptokrona.sdk.model.util;

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

	private long height;
	private long minMixin;
	private long maxMixin;
	private long defaultMixin;

	public MixinLimit(int height, long minMixin, long maxMixin, long defaultMixin) {
		this.height = height;
		this.minMixin = minMixin;
		this.maxMixin = maxMixin;
		this.defaultMixin = defaultMixin;
	}

	public MixinLimit(int height, long minMixin) {
		this.height = height;
		this.minMixin = minMixin;
		this.maxMixin = minMixin;
		this.defaultMixin = minMixin;
	}
}
