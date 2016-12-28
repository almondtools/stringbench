package com.almondtools.stringbench.analyzer.json;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Result implements Comparable<Result> {

	@JsonProperty
	public int alphabet;
	@JsonProperty
	public int pattern;
	@JsonProperty
	public double score;
	@JsonProperty
	@JsonFormat(pattern="yyyy-MM-dd", timezone="CET")
	public Date date;
	

	public Result(int alphabet, int pattern, double score, Date date) {
		this.alphabet = alphabet;
		this.pattern = pattern;
		this.score = score;
		this.date = date;
	}


	@Override
	public int compareTo(Result that) {
		int compare = Integer.compare(this.alphabet, that.alphabet);
		if (compare == 0) {
			compare = Integer.compare(this.pattern, that.pattern);
		}
		if (compare == 0) {
			compare = Double.compare(this.score, that.score);
		}
		if (compare == 0) {
			compare = this.date.compareTo(that.date);
		}
		return compare;
	}
	
}
