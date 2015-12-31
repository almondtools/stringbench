package com.almondtools.stringbench;



public class ResultSizeNotAcceptedException extends RuntimeException {

	private int expected;
	private int actual;

	public ResultSizeNotAcceptedException(int expected, int actual) {
		this.expected = expected;
		this.actual = actual;
	}

	public int getExpected() {
		return expected;
	}
	
	public int getActual() {
		return actual;
	}
}
