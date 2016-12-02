package com.almondtools.stringbench.generator;

public enum AlphabetOption {
	NORMAL_DISTRIBUTED, SPARSE;

	public boolean in(AlphabetOption[] options) {
		for (int i = 0; i < options.length; i++) {
			if (this == options[i]) {
				return true;
			}
		}
		return false;
	}
}
