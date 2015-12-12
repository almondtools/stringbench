package com.almondtools.stringbench;

import java.util.List;


public class ResultNotAcceptedException extends RuntimeException {

	private List<Integer> expected;
	private List<Integer> actual;

	public ResultNotAcceptedException(List<Integer> expected, List<Integer> actual) {
		this.expected = expected;
		this.actual = actual;
	}

	public List<Integer> getExpected() {
		return expected;
	}

	public List<Integer> getActual() {
		return actual;
	}
	
}
