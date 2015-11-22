package com.almondtools.stringbench;

import com.eaio.stringsearch.BoyerMooreHorspoolRaita;
import com.eaio.stringsearch.StringSearch;

public class SSBoyerMooreHorspoolRaitaBenchmark extends StringSearchBenchmark {

	private static final String ID = "Stringsearch Boyer-Moore-Horspool-Raita";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public StringSearch create() {
		return new BoyerMooreHorspoolRaita();
	}

}
