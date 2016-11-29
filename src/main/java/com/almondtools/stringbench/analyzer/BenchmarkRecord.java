package com.almondtools.stringbench.analyzer;

import java.util.Date;

public class BenchmarkRecord {

	private Date date;
	private String algorithm;
	private String type;
	private String family;
	private int alphabet;
	private int pattern;
	private int number;
	private double score;

	public BenchmarkRecord() {
	}

	public BenchmarkRecord withDate(Date date) {
		this.date = date;
		return this;
	}

	public BenchmarkRecord withFamily(String family) {
		this.family = family;
		return this;
	}

	public BenchmarkRecord withType(String type) {
		this.type = type;
		return this;
	}

	public BenchmarkRecord withAlgorithm(String algorithm) {
		this.algorithm = algorithm;
		return this;
	}

	public Date getDate() {
		return date;
	}

	public void setBenchmark(String benchmark) {
		int last = benchmark.lastIndexOf(".");
		this.algorithm = benchmark.substring(0, last);
		this.type = benchmark.substring(last + 1);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public int getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(int alphabet) {
		this.alphabet = alphabet;
	}

	public int getPattern() {
		return pattern;
	}

	public void setPattern(int pattern) {
		this.pattern = pattern;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return algorithm + '(' + alphabet + ", " + pattern + ", " + number + ')';
	}

}