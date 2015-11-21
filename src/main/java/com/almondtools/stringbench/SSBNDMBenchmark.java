package com.almondtools.stringbench;

import com.eaio.stringsearch.BNDM;
import com.eaio.stringsearch.StringSearch;

public class SSBNDMBenchmark extends StringSearchBenchmark {

	private static final String ID = "Stringsearch BNDM";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public StringSearch create() {
		return new BNDM();
	}

}
