package com.almondtools.stringbench.analyzer.json;


import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Candidate implements Comparable<Candidate> {

	@JsonProperty
	public String algorithm;
	@JsonProperty
	public String family;
	@JsonProperty
	public SortedSet<Result> results;

	
	public Candidate(String benchmark, String family, Collection<Result> results) {
		this.algorithm = benchmark;
		this.family = family;
		this.results = new TreeSet<>(results);
	}


	@Override
	public int compareTo(Candidate that) {
		int compare = this.family.compareTo(that.family);
		if (compare == 0) {
			compare = this.algorithm.compareTo(that.algorithm);
		}
		return compare;
	}

}
