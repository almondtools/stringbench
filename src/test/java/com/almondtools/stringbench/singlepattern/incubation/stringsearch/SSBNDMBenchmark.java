package com.almondtools.stringbench.singlepattern.incubation.stringsearch;

import static com.almondtools.stringbenchanalyzer.Family.FACTOR;

import com.almondtools.stringbenchanalyzer.Family;
import com.eaio.stringsearch.BNDM;
import com.eaio.stringsearch.StringSearch;

public class SSBNDMBenchmark extends StringSearchBenchmark {

	private static final String ID = "Stringsearch BNDM";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public Family getFamily() {
		return FACTOR;
	}
	
	@Override
	public StringSearch create() {
		return new BNDM();
	}

}
