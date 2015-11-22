package com.almondtools.stringbench;

import com.eaio.stringsearch.BoyerMooreHorspool;
import com.eaio.stringsearch.StringSearch;

public class SSBoyerMooreHorspoolBenchmark extends StringSearchBenchmark {

	private static final String ID = "Stringsearch Boyer-Moore-Horspool";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public StringSearch create() {
		return new BoyerMooreHorspool();
	}

}
