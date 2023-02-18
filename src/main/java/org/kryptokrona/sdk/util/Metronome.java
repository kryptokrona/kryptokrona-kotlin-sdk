// Copyright (c) 2022-2023, The Kryptokrona Developers
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

package org.kryptokrona.sdk.util;

import io.reactivex.rxjava3.core.Flowable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Metronome.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class Metronome {

	private AtomicLong elapsedTime;

	private AtomicBoolean resumed;

	private AtomicBoolean stopped;

	private final long interval;

	public Metronome(long interval) {
		this.elapsedTime = new AtomicLong();
		this.resumed = new AtomicBoolean();
		this.stopped = new AtomicBoolean();
		this.interval = interval;
	}

	public Flowable<Long> start() {
		resumed.set(true);
		stopped.set(false);

		return Flowable.interval(this.interval, TimeUnit.MILLISECONDS)
			.takeWhile(tick -> !stopped.get())
			.filter(tick -> resumed.get())
			.map(tick -> elapsedTime.addAndGet(1000));
	}

	public void pause() {
		resumed.set(false);
	}

	public void resume() {
		resumed.set(true);
	}

	public void stop() {
		stopped.set(true);
	}

	public void addToTimer(int seconds) {
		elapsedTime.addAndGet(seconds * 1000L);
	}
}
