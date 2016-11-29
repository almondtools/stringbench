package com.almondtools.stringbench.analyzer.json;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {

	@JsonProperty
	public int alphabet;
	@JsonProperty
	public int pattern;
	@JsonProperty
	public double score;
	@JsonProperty
	@JsonFormat(pattern="yyyy-MM-dd")
	public Date date;
	

	public Result(int alphabet, int pattern, double score, Date date) {
		this.alphabet = alphabet;
		this.pattern = pattern;
		this.score = score;
		this.date = date;
	}

	
}
