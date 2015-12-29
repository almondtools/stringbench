package com.almondtools.stringbench;

import java.util.List;
import java.util.Map;

class Text {
	public String sample;
	public Map<String, List<Integer>> patterns;
	public List<Integer> all;

	public Text(String text, Map<String, List<Integer>> patterns, List<Integer> all) {
		this.sample = text;
		this.patterns = patterns;
		this.all = all;
	}

}