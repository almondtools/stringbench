package com.almondtools.stringbenchgenerator;

import static com.almondtools.stringbenchgenerator.AlphabetOption.NORMAL_DISTRIBUTED;
import static com.almondtools.stringbenchgenerator.AlphabetOption.SPARSE;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import java.util.stream.Stream;

public class GenerateSamples {

	private static final Pattern PATTERN_LINE = Pattern.compile(":(\\d+)$");

	private static final int[] ALPHABET_SIZES = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024 };
	private static AlphabetOption[][] OPTIONS = { { SPARSE }, { NORMAL_DISTRIBUTED }, {} };

	private static final int[] PATTERN_SIZES = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024 };
	private static final int[] PATTERN_NUMBERS = { 2, 8, 32, 128 };

	private static final int CHAR_NUMBER = 3 * 1024 * 1024;

	private int alphabetSize;
	private AlphabetOption[] options;
	private Random random;

	private String sample;

	public GenerateSamples(int alphabetSize, AlphabetOption... options) {
		this.alphabetSize = alphabetSize;
		this.options = options;
	}

	public static String computeKey(int alphabetSize, AlphabetOption... options) {
		return "sample-" + alphabetSize + optionsKey(options);
	}

	public static String computeKey(int alphabetSize, int patternSize, AlphabetOption... options) {
		return "pattern-" + alphabetSize + "-" + patternSize + optionsKey(options);
	}

	private static String optionsKey(AlphabetOption... options) {
		return options.length == 0 ? "" : Stream.of(options)
			.map(option -> option.toString().toLowerCase())
			.collect(joining("-", "-", ""));
	}

	public static void main(String[] args) throws IOException {
		String base = args[0];
		Path basePath = Paths.get(base);
		if (!Files.exists(basePath)) {
			Files.createDirectories(basePath);
		}
		int[] alphabetSizes = ALPHABET_SIZES;
		if (args.length >= 2) {
			alphabetSizes = new int[]{Integer.parseInt(args[1])};
		}
		for (int alphabetSize : alphabetSizes) {
			for (AlphabetOption[] options : OPTIONS) {
				GenerateSamples generator = new GenerateSamples(alphabetSize, options);
				generator.writeSample(basePath);
				generator.writePatterns(basePath, PATTERN_SIZES, PATTERN_NUMBERS);
			}
		}
	}

	private void writeSample(Path base) throws IOException {
		String key = computeKey(alphabetSize, options);
		Path target = base.resolve(key + ".sample");

		sample = generateSample();

		System.out.println("writing " + target.toString());
		Files.write(target, sample.getBytes());
	}

	private void writePatterns(Path base, int[] patternSizes, int[] patternNumbers) throws IOException {
		int maxPatternNumber = maxNumber(patternNumbers);
		for (int patternSize : patternSizes) {
			String key = computeKey(alphabetSize, patternSize, options);

			Set<String> patterns = writeSingleResults(base, key, patternSize, maxPatternNumber);

			writeMultiResults(base, key, patterns, patternNumbers);
		}
	}

	public Set<String> writeSingleResults(Path base, String key, int patternSize, int maxPatternNumber) throws IOException {
		Path target = base.resolve(key + ".result");

		List<String> lines = new ArrayList<String>();

		Map<String, List<Integer>> patterns = generatePatterns(patternSize, maxPatternNumber);
		for (Map.Entry<String, List<Integer>> entry : patterns.entrySet()) {
			lines.add(escape(entry.getKey()) + ":" + entry.getValue().size());
		}

		System.out.println("writing " + target.toString());
		Files.write(target, lines);

		return patterns.keySet();
	}

	public void writeMultiResults(Path base, String key, Set<String> patterns, int[] patternNumbers) throws IOException {
		Path target = base.resolve(key + ".multi.result");

		List<String> lines = new ArrayList<String>();

		Map<Integer, List<Integer>> all = generateAllMatches(patterns, patternNumbers);
		for (Map.Entry<Integer, List<Integer>> entry : all.entrySet()) {
			lines.add(entry.getKey() + ":" + entry.getValue().size());
		}

		System.out.println("writing " + target.toString());
		Files.write(target, lines);
	}

	public static int maxNumber(int[] patternNumbers) {
		int maxPatternNumber = 0;
		for (int patternNumber : patternNumbers) {
			maxPatternNumber = maxPatternNumber < patternNumber ? patternNumber : maxPatternNumber;
		}
		return maxPatternNumber;
	}

	public void resetRandom(int i) {
		random = new Random(i);
	}

	public String getSample() {
		return sample;
	}

	public String generateSample() {
		if (sample != null) {
			return sample;
		}
		resetRandom(31);
		char[] alphabet = generateAlphabet(options);
		Supplier<Integer> distribution = generateDistribution(options);
		sample = generateSample(alphabet, distribution);
		return sample;
	}

	public Map<String, List<Integer>> generatePatterns(int patternSize, int patternNumber) {
		if (sample == null) {
			generateSample();
		}
		Map<String, List<Integer>> patterns = new LinkedHashMap<>();
		for (int i = 0; i < patternNumber; i++) {
			resetRandom(i);
			String pattern = findPattern(patternSize);
			if (!patterns.containsKey(pattern)) {
				List<Integer> occurences = findOccurences(pattern);
				patterns.put(pattern, occurences);
			}
		}
		return patterns;
	}

	public Map<Integer, List<Integer>> generateAllMatches(Set<String> patterns, int[] patternNumbers) {
		Map<Integer, List<Integer>> all = new LinkedHashMap<Integer, List<Integer>>();
		for (int number : patternNumbers) {
			all.put(number, findOccurences(patterns.stream()
				.limit(number)
				.distinct()
				.collect(toSet())));
		}
		return all;
	}

	private String findPattern(int patternSize) {
		int pos = random.nextInt(sample.length() - patternSize);
		return sample.substring(pos, pos + patternSize);
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
		char[] buffer = new char[CHAR_NUMBER];
		for (int i = 0; i < CHAR_NUMBER; i++) {
			int index = distribution.get();
			buffer[i] = alphabet[index];
		}
		return new String(buffer);
	}

	private char[] generateAlphabet(AlphabetOption... options) {
		Function<Integer, Character> chars = generateChars(options);
		char[] alphabet = new char[alphabetSize];
		for (int i = 0; i < alphabet.length; i++) {
			alphabet[i] = chars.apply(i);
		}
		return alphabet;
	}

	private Function<Integer, Character> generateChars(AlphabetOption... options) {
		if (AlphabetOption.SPARSE.in(options)) {
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

	private Supplier<Integer> generateDistribution(AlphabetOption... options) {
		if (AlphabetOption.NORMAL_DISTRIBUTED.in(options)) {
			return this::normalDistribution;
		} else {
			return this::equalDistribution;
		}
	}

	private Integer equalDistribution() {
		return random.nextInt(alphabetSize);
	}

	private Integer normalDistribution() {
		double mean = (double) alphabetSize / 2d;
		double var = (double) alphabetSize / 6d;
		int result = (int) Math.round(random.nextGaussian() * var + mean);
		if (result < 0) {
			return 0;
		} else if (result >= alphabetSize) {
			return alphabetSize - 1;
		} else {
			return result;
		}
	}

	public static String readSample(String sampleKey) throws IOException {
		try (BufferedReader reader = open(sampleKey + ".sample")) {
			StringBuilder buffer = new StringBuilder();
			char[] chars = new char[8192];
			int n = 0;
			while ((n = reader.read(chars)) > -1) {
				buffer.append(chars, 0, n);
			}
			return buffer.toString();
		}
	}

	public static Map<String, Integer> readPatterns(String resultKey) throws IOException {
		try (BufferedReader reader = open(resultKey + ".result")) {
			return reader.lines()
				.map(line -> unescape(line))
				.map(line -> splitPattern(line))
				.collect(toMap(value -> value[0], value -> Integer.parseInt(value[1]), (v1, v2) -> Math.max(v1, v2), LinkedHashMap::new));
		}

	}

	public static Map<Integer, Integer> readAll(String resultKey) throws IOException {
		try (BufferedReader reader = open(resultKey + ".multi.result")) {
			return reader.lines()
				.map(line -> unescape(line))
				.map(line -> splitPattern(line))
				.collect(toMap(value -> Integer.parseInt(value[0]), value -> Integer.parseInt(value[1])));
		}
	}

	public static BufferedReader open(String fileName) throws IOException {
		System.out.println(fileName);
		return new BufferedReader(new InputStreamReader(GenerateSamples.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
	}

	private static String[] splitPattern(String line) {
		Matcher m = PATTERN_LINE.matcher(line);
		if (m.find()) {
			int pos = m.start();
			return new String[] { line.substring(0, pos), m.group(1) };
		} else {
			return new String[] { line, "0" };
		}
	}

	private static String escape(String str) {
		return str
			.replace("\\", "\\\\")
			.replace("\r", "\\r")
			.replace("\n", "\\n");
	}

	private static String unescape(String str) {
		StringBuilder buffer = new StringBuilder();
		boolean escaped = false;
		for (char c : str.toCharArray()) {
			if (escaped) {
				if (c == 'r') {
					buffer.append('\r');
				} else if (c == 'n') {
					buffer.append('\n');
				} else {
					buffer.append(c);
				}
				escaped = false;
			} else if (c == '\\') {
				escaped = true;
			} else {
				buffer.append(c);
			}
			
		}
		return buffer.toString();
	}

}
