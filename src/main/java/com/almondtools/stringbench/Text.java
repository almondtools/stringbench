package com.almondtools.stringbench;

import java.util.Map;

public class Text {
	public String sample;
	public Map<String, Integer> patterns;
	public Map<Integer, Integer> all;

	public Text(String sample, Map<String, Integer> patterns, Map<Integer, Integer> all) {
		this.sample = sample;
		this.patterns = patterns;
		this.all = all;
	}

}