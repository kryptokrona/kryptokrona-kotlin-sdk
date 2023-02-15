// Copyright (c) 2022-2022, The Kryptokrona Project
//
// Written by Marcus Cvjeticanin
//
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without modification, are
// permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice, this list of
//    conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright notice, this list
//    of conditions and the following disclaimer in the documentation and/or other
//    materials provided with the distribution.
//
// 3. Neither the name of the copyright holder nor the names of its contributors may be
//    used to endorse or promote products derived from this software without specific
//    prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
// EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
// THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
// STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
// THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package org.mjovanc.kryptokrona.model.util;

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
