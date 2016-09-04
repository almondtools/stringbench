package com.almondtools.stringbench.singlepattern;

import java.io.IOException;

import com.almondtools.stringbench.singlepattern.SinglePatternSample;

public abstract class SinglePatternTest {

	protected static SinglePatternSample createSample(int alphabet, int pattern) {
		try {
			System.out.println("creating sample with alphabet size " + alphabet + " and pattern size " + pattern);
			SinglePatternSample sample = new SinglePatternSample();
			sample.setAlphabetSize(alphabet);
			sample.setPatternSize(pattern);
			sample.setup();
			return sample;
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}

}
