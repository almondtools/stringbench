package com.almondtools.stringbench.multipattern;

import java.io.IOException;

public abstract class MultiPatternTest {

	protected static MultiPatternSample createSample(int alphabet, int pattern, int patternNumber) {
		try {
			System.out.println("creating sample with alphabet size " + alphabet + " and pattern size " + pattern + " (" + patternNumber + " patterns)");
			MultiPatternSample sample = new MultiPatternSample();
			sample.setPatternNumber(patternNumber);
			sample.setAlphabetSize(alphabet);
			sample.setPatternSize(pattern);
			sample.setup();
			return sample;
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}

}
