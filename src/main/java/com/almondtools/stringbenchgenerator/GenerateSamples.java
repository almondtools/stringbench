package com.almondtools.stringbenchgenerator;

import static java.util.stream.Collectors.joining;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GenerateSamples {

	private static final int FILE_SIZE = 3 * 1024 * 1024;
	
	private String base;
	private int alphabetSize;
	private GeneratorOption[] options;
	private Random random;


	public GenerateSamples(String base, int alphabetSize, GeneratorOption... options) {
		this.base = base ;
		this.alphabetSize = alphabetSize;
		this.options = options;
		this.random = new Random(31);
	}

	public static void main(String[] args) throws IOException {
		String base = args[0];
		int alphabetSize = Integer.parseInt(args[1]);
		GeneratorOption[] options = Stream.of(args)
			.skip(2)
			.map(GeneratorOption::valueOf)
			.toArray(GeneratorOption[]::new);
		GenerateSamples processResults = new GenerateSamples(base, alphabetSize, options);
		processResults.run();
	}

	public void run() throws IOException {
		char[] alphabet = generateAlphabet();
		Supplier<Integer> distribution = generateDistribution();
		generateSample(alphabet, distribution);
	}

	private void generateSample(char[] alphabet, Supplier<Integer> distribution) throws IOException {
		String config = alphabetSize
			+ (options.length == 0 ? "" : "-")
			+ Stream.of(options)
				.map(option -> option.toString().toLowerCase())
				.collect(joining("-"));
		String name = "sample-" + config + ".smp";
		Path basePath = Paths.get(base);
		if (!Files.exists(basePath)) {
			Files.createDirectories(basePath);
		}
		Path path = basePath.resolve(name);
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			for (int i = 0; i < FILE_SIZE; i++) {
				int index = distribution.get();
				char c = alphabet[index];
				writer.write(c);
			}
		}

	}

	private char[] generateAlphabet() {
		Function<Integer, Character> chars = generateChars();
		char[] alphabet = new char[alphabetSize];
		for (int i = 0; i < alphabet.length; i++) {
			alphabet[i] = chars.apply(i);
		}
		return alphabet;
	}

	private Function<Integer, Character> generateChars() {
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

	private Supplier<Integer> generateDistribution() {
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
