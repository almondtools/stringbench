package com.almondtools.stringbench.analyzer.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BenchmarkCollection {

	@JsonProperty
	private int number;
	@JsonProperty
	private String type;
	@JsonProperty
	private List<Candidate> candidates;

	public BenchmarkCollection(int number, String type, List<Candidate> candidates) {
		this.number = number;
		this.type = type;
		this.candidates = candidates;
	}

}
