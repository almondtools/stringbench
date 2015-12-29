package com.almondtools.stringbenchgenerator;

public enum GeneratorOption {
	NORMAL_DISTRIBUTED, SPARSE;

	public boolean in(GeneratorOption[] options) {
		for (int i = 0; i < options.length; i++) {
			if (this == options[i]) {
				return true;
			}
		}
		return false;
	}
}
