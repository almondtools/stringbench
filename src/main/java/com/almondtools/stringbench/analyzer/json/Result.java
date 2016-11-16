package com.almondtools.stringbench.analyzer.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {

	@JsonProperty
	public int alphabet;
	@JsonProperty
	public int pattern;
	@JsonProperty
	public double score;

	public Result(int alphabet, int pattern, double score) {
		this.alphabet = alphabet;
		this.pattern = pattern;
		this.score = score;
	}

	
}
