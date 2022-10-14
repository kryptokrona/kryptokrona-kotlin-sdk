package org.kryptokrona.sdk.util;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.config.Config;

/**
 * Metronome.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class Metronome {

	private long interval;

	private String timer; // change this to correct data type

	private boolean shouldStop;

	private boolean inTick;

	private boolean started;

	public Metronome(long interval) {
		this.interval = interval;
	}

	public Observable<Void> start() {
		return Observable.empty();
	}

	public Observable<Void> stop() {
		return Observable.empty();
	}

	public Observable<Void> tick() {
		return Observable.empty();
	}
}
