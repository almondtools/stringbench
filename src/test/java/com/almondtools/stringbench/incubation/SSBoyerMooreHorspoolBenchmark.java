package com.almondtools.stringbench.incubation;

import static com.almondtools.stringbenchanalyzer.Family.SUFFIX;

import com.almondtools.stringbench.StringSearchBenchmark;
import com.almondtools.stringbenchanalyzer.Family;
import com.eaio.stringsearch.BoyerMooreHorspool;
import com.eaio.stringsearch.StringSearch;

public class SSBoyerMooreHorspoolBenchmark extends StringSearchBenchmark {

	private static final String ID = "Stringsearch Boyer-Moore-Horspool";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public Family getFamily() {
		return SUFFIX;
	}

	@Override
	public StringSearch create() {
		return new BoyerMooreHorspool();
	}

}
