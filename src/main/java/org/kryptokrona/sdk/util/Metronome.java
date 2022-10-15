package org.kryptokrona.sdk.util;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.config.Config;
import org.reactivestreams.Subscription;

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
