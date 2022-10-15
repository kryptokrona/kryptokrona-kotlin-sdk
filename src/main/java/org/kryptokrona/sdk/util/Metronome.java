package org.kryptokrona.sdk.util;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.config.Config;

/**
 * Metronome.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class Metronome {

	private long bpm;

	private String timer; // change this to correct data type

	private boolean shouldStop;

	private boolean inTick;

	private boolean started;

	public Metronome(long bpm) {
		this.started = true;
		this.bpm = bpm;
	}

	public Observable<String> start() {
		while (started)
		{
			try
			{
				Thread.sleep((long) (1000 * (60.0 / bpm)));
			}
			catch(InterruptedException e)
			{
				//TODO: perhaps add logger heres
				e.printStackTrace();
			}

			System.out.println("RUNNING");
		}
		return Observable.empty();
	}

	public Observable<String> stop() {
		started = false;
		return Observable.empty();
	}

	public Observable<String> tick() {
		return Observable.empty();
	}
}
