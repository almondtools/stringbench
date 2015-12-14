package com.almondtools.stringbench;

import static com.almondtools.stringbenchanalyzer.Family.SUFFIX;

import java.util.List;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.stringbenchanalyzer.Family;
import com.almondtools.stringsandchars.search.SetHorspool;
import com.almondtools.stringsandchars.search.StringSearchAlgorithm;

@State(Scope.Thread)
public class SCSetHorspoolBenchmark extends StringsAndCharsMultiBenchmark {

	private static final String ID = "Strings and Chars Set Horspool";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public Family getFamily() {
		return SUFFIX;
	}

	@Override
	public StringSearchAlgorithm create(List<String> pattern) {
		return new SetHorspool(pattern);
	}

}
