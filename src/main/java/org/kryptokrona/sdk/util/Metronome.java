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

	private boolean started;

	private long bpm;

	public Metronome(long bpm) {
		this.elapsedTime = new AtomicLong();
		this.resumed = new AtomicBoolean();
		this.stopped = new AtomicBoolean();
		this.started = false;
		this.bpm = bpm;
	}

	public Flowable<Long> start() {
		resumed.set(true);
		stopped.set(false);

		return Flowable.interval(1, TimeUnit.SECONDS)
				.takeWhile(tick -> !stopped.get())
				.filter(tick -> resumed.get())
				.map(tick -> elapsedTime.addAndGet(1000));
	}

	public void pauseTimer() {
		resumed.set(false);
	}

	public void resumeTimer() {
		resumed.set(true);
	}

	public void stopTimer() {
		stopped.set(true);
	}

	public void addToTimer(int seconds) {
		elapsedTime.addAndGet(seconds * 1000L);
	}
}
