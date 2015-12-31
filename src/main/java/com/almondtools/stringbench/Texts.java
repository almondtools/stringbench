package com.almondtools.stringbench;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.joining;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.almondtools.stringbenchgenerator.GenerateSamples;
import com.almondtools.stringbenchgenerator.GeneratorOption;

public class Texts {

	private static final Pattern KEY = Pattern.compile("sample-(\\d+)-(\\d+)((-\\w+)*)");
	private static final Pattern SEPARATOR = Pattern.compile("-");

	public static volatile Texts TEXTS = new Texts();

	private Map<Integer, GenerateSamples> generators;
	private Map<String, Text> texts;

	public Texts() {
		this.generators = new HashMap<>();
		this.texts = new HashMap<>();
	}

	public static String computeKey(int size, int length, GeneratorOption[] options) {
		String optionsKey = options.length == 0 ? "" : Stream.of(options)
			.map(option -> option.toString().toLowerCase())
			.collect(joining("-", "-", ""));

		return "sample-" + size + "-" + length + optionsKey;
	}

	public Text generate(String key, int number) {
		Matcher m = KEY.matcher(key);
		if (m.matches()) {
			int size = Integer.parseInt(m.group(1));
			int length = Integer.parseInt(m.group(2));
			GeneratorOption[] options = SEPARATOR.splitAsStream(m.group(3))
				.map(part -> GeneratorOption.valueOf(part.toUpperCase()))
				.toArray(GeneratorOption[]::new);
			GenerateSamples generator = generators.computeIfAbsent(size, s -> new GenerateSamples(size));	
			String sample = generator.generateSample(options);
			Map<String, List<Integer>> patterns = generator.generatePatterns(length, number);
			List<Integer> all = generator.generateAllMatches(length, number);
			
			return new Text(sample, patterns, all);
		}
		return new Text(null, emptyMap(), emptyList());
	}

	public Text text(String key, int number) {
		return texts.computeIfAbsent(key, k -> generate(k, number));
	}

}
