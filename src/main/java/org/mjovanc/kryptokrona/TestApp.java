package org.mjovanc.kryptokrona;

public class TestApp {
	public native void print();

	static {
		System.loadLibrary("testapp");
	}

	public static void main(String[] args) {
		new TestApp().print();
	}
}
