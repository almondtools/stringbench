package com.almondtools.stringbench.singlepattern.incubation;

import static com.almondtools.stringbenchanalyzer.Family.SUFFIX;

import com.almondtools.stringbenchanalyzer.Family;
import com.eaio.stringsearch.BoyerMooreHorspoolRaita;
import com.eaio.stringsearch.StringSearch;

public class SSBoyerMooreHorspoolRaitaBenchmark extends StringSearchBenchmark {

	private static final String ID = "Stringsearch Boyer-Moore-Horspool-Raita";

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
		return new BoyerMooreHorspoolRaita();
	}

}
