package com.almondtools.stringbench.analyzer.json;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BenchmarkCollection implements Comparable<BenchmarkCollection> {

	@JsonProperty
	private int number;
	@JsonProperty
	private String type;
	@JsonProperty
	private SortedSet<Candidate> candidates;

	public BenchmarkCollection(int number, String type, Collection<Candidate> candidates) {
		this.number = number;
		this.type = type;
		this.candidates = new TreeSet<>(candidates);
	}

	@Override
	public int compareTo(BenchmarkCollection that) {
		int compare = Integer.compare(this.number, that.number);
		if (compare == 0) {
			compare = this.type.compareTo(that.type);
		}
		return compare;
	}
}
