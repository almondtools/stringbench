package com.almondtools.stringbench.scalingpattern;

import java.io.IOException;

public abstract class ScalingPatternTest {

	protected static ScalingPatternSample createSample(String patternCorpus, int patternNumber) {
		try {
			System.out.println("creating sample with corpus " + patternCorpus + " (" + patternNumber + " patterns)");
			ScalingPatternSample sample = new ScalingPatternSample();
			sample.setPatternNumber(patternNumber);
			sample.setPatternCorpus(patternCorpus);;
			sample.setup();
			return sample;
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}

}
