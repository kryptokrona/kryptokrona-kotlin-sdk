package org.kryptokrona.sdk.model.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private long defaultMixin;

	public MixinLimits(List<MixinLimit> limits, int defaultMixin) {
		this.limits = limits.stream()
				.sorted(Comparator.comparing(MixinLimit::getHeight).reversed())
				.collect(Collectors.toList());
		this.defaultMixin = defaultMixin;
	}

	/**
	 * Returns the default mixin for the given height.
	 *
	 * @param height : long
	 * @return long
	 */
	public long getDefaultMixinByHeight(long height) {
		/* No limits defined, or height is before first limit */
		if (limits.size() == 0) {
			return defaultMixin;
		}

		for (MixinLimit limit : this.limits) {
			if (height > limit.getHeight()) {
				return limit.getDefaultMixin();
			}
		}

		throw new RuntimeException("Something happened :(");
	}

	/**
	 * Returns the minimum and maximum mixin for the given height.
	 *
	 * @param height : long
	 * @return HashMap
	 */
	public Map<String, Long> getMixinLimitsByHeight(long height) {
		long minimumMixin = 0;
		long maximumMixin = (long) Math.pow(2, 64);
		HashMap<String, Long> minMaxMixin = new HashMap<>();

		for (var limit : limits) {
			var test = limit.getHeight();
			if (height > limit.getHeight()) {
				minMaxMixin.put("minMixin", limit.getMinMixin());
				minMaxMixin.put("maxMixin", limit.getMaxMixin());
			}
		}

		return minMaxMixin;
	}
}
