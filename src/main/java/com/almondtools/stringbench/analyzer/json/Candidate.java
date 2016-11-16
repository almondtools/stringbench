package com.almondtools.stringbench.analyzer.json;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Candidate {

	@JsonProperty
	public String algorithm;
	@JsonProperty
	public String family;
	@JsonProperty
	public List<Result> results;

	
	public Candidate(String benchmark, String family, List<Result> results) {
		this.algorithm = benchmark;
		this.family = family;
		this.results = results;
	}

}
