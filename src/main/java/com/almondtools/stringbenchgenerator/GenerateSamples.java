package com.almondtools.stringbenchgenerator;

import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateSamples {

	private static final int FILE_SIZE = 3 * 1024 * 1024;
	
	private int alphabetSize;
	private Random random;

	private String sample;
	private Map<String, List<Integer>> patterns;
	private int number;



	public GenerateSamples(int alphabetSize) {
		this.alphabetSize = alphabetSize;
	}
	
	public void resetRandom(int i) {
		random = new Random(i);
	}

	public String getSample() {
		return sample;
	}

	public Map<String, List<Integer>> getPatterns() {
		return patterns;
	}

	public String generateSample(GeneratorOption... options) {
		if (sample != null) {
			return sample;
		}
		resetRandom(31);
		char[] alphabet = generateAlphabet(options);
		Supplier<Integer> distribution = generateDistribution(options);
		sample = generateSample(alphabet, distribution);
		return sample;
	}

	public Map<String,List<Integer>> generatePatterns(int length, int number) {
		if (patterns != null && this.number >= number) {
			return patterns;
		}
		if (patterns == null) {
			patterns = new LinkedHashMap<>();
		}
		for (int i = this.number; i < number; i++) {
			resetRandom(i);
			String pattern = findPattern(length);
			if (!patterns.containsKey(pattern)) {
				List<Integer> occurences = findOccurences(pattern);
				patterns.put(pattern, occurences);
			}
		}
		this.number = number;
		return patterns;
	}

	public List<Integer> generateAllMatches(int length, int number) {
		if (patterns != null && this.number >= number) {
			return findOccurences(patterns.keySet().stream()
				.limit(number)
				.collect(toSet()));
		}
		return null;
	}

	private String findPattern(int length) {
		int pos = random.nextInt(sample.length() - length);
		return sample.substring(pos, pos + length);
	}

	private List<Integer> findOccurences(String pattern) {
		List<Integer> occurences = new ArrayList<>();
		Pattern p = Pattern.compile(Pattern.quote(pattern));
		Matcher m = p.matcher(sample);
		while (m.find()) {
			occurences.add(m.start());
		}
		return occurences;
	}

	private List<Integer> findOccurences(Set<String> patterns) {
		String[] sortedpatterns = patterns.toArray(new String[0]);
		Arrays.sort(sortedpatterns, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
		});
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < sortedpatterns.length; i++) {
			buffer.append('|');
			buffer.append(Pattern.quote(sortedpatterns[i]));
		}

		List<Integer> occurences = new ArrayList<>();
		Pattern p = Pattern.compile(buffer.substring(1));
		Matcher m = p.matcher(sample);
		while (m.find()) {
			occurences.add(m.start());
		}
		return occurences;
	}

	private String generateSample(char[] alphabet, Supplier<Integer> distribution) {
		char[] buffer = new char[FILE_SIZE];
		for (int i = 0; i < FILE_SIZE; i++) {
			int index = distribution.get();
			buffer[i] = alphabet[index];
		}
		return new String(buffer);
	}

	private char[] generateAlphabet(GeneratorOption... options) {
		Function<Integer, Character> chars = generateChars(options);
		char[] alphabet = new char[alphabetSize];
		for (int i = 0; i < alphabet.length; i++) {
			alphabet[i] = chars.apply(i);
		}
		return alphabet;
	}

	private Function<Integer, Character> generateChars(GeneratorOption... options) {
		if (GeneratorOption.SPARSE.in(options)) {
			return this::sparseChars;
		} else if (alphabetSize == 2) {
			return this::binaryChars;
		} else if (alphabetSize == 4) {
			return this::dnaChars;
		} else if (alphabetSize <= 96) {
			return this::asciiChars;
		} else {
			return this::zeroBasedChars;
		}
	}

	private Character sparseChars(Integer index) {
		return shuffle((char) index.intValue());
	}

	private char shuffle(char c) {
		int i = Integer.rotateLeft(c, 4);
		char mappedC = (char) (i ^ 0b01010101_01010101);
		return mappedC;
	}

	private Character binaryChars(Integer index) {
		return index.toString().charAt(0);
	}

	private Character dnaChars(Integer index) {
		switch (index) {
		case 0:
			return 'G';
		case 1:
			return 'A';
		case 2:
			return 'C';
		case 3:
			return 'T';
		default:
			return ' ';
		}
	}

	private Character asciiChars(Integer index) {
		if (index == 0) {
			return ' ';
		} else if (index == 96) {
			return (char) 0x7f;
		} else {
			return (char) (0x7f - index);
		}
	}

	private Character zeroBasedChars(Integer index) {
		return (char) index.intValue();
	}

	private Supplier<Integer> generateDistribution(GeneratorOption... options) {
		if (GeneratorOption.NORMAL_DISTRIBUTED.in(options)) {
			return this::normalDistribution;
		} else {
			return this::equalDistribution;
		}
	}

	private Integer equalDistribution() {
		return random.nextInt(alphabetSize);
	}

	private Integer normalDistribution() {
		int mean = alphabetSize / 2;
		int var = alphabetSize / 6;
		int result = (int) (random.nextGaussian() * var + mean);
		if (result < 0) {
			return 0;
		} else if (result >= alphabetSize) {
			return alphabetSize - 1;
		} else {
			return result;
		}
	}

}
