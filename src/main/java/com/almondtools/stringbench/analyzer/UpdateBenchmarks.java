package com.almondtools.stringbench.analyzer;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.toList;
import static org.simpleflatmapper.csv.CsvColumnDefinition.customReaderDefinition;
import static org.simpleflatmapper.csv.CsvColumnDefinition.identity;
import static org.simpleflatmapper.csv.CsvColumnDefinition.ignoreDefinition;
import static org.simpleflatmapper.csv.CsvColumnDefinition.renameDefinition;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.simpleflatmapper.csv.CellValueReader;
import org.simpleflatmapper.csv.CsvColumnDefinition;
import org.simpleflatmapper.csv.CsvParser;
import org.simpleflatmapper.csv.CsvParser.MapToDSL;
import org.simpleflatmapper.csv.ParsingContext;

import com.almondtools.stringbench.analyzer.json.BenchmarkCollection;
import com.almondtools.stringbench.analyzer.json.Candidate;
import com.almondtools.stringbench.analyzer.json.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class UpdateBenchmarks {

	private static final Pattern BENCHMARK_FILE = Pattern.compile("result(-[ans]*)?-(\\d{4}-\\d{2}-\\d{2})(-\\d+)?\\.csv");

	private Map<String, String> names;
	private Map<String, String> families;
	private BenchmarkRecords benchmarks;

	public UpdateBenchmarks() {
		names = new HashMap<>();
		families = new HashMap<>();
		benchmarks = new BenchmarkRecords();
	}

	public static void main(String[] args) throws IOException {
		UpdateBenchmarks updateBenchmarks = new UpdateBenchmarks();
		updateBenchmarks.run();
	}

	public void run() throws IOException {
		Path root = Paths.get("benchmarkresults");
		List<File> csvFiles = Files.list(root)
			.filter(path -> BENCHMARK_FILE.matcher(path.getFileName().toString()).matches())
			.map(path -> path.toFile())
			.collect(toList());

		File namesFile = root.resolve("names.csv").toFile();
		CsvParser
			.mapTo(Mapping.class)
			.columnDefinition("name", renameDefinition("from"))
			.columnDefinition("normalization", renameDefinition("to"))
			.forEach(namesFile, mapping -> names.put(mapping.from, mapping.to));

		File familiesFile = root.resolve("families.csv").toFile();
		CsvParser
			.mapTo(Mapping.class)
			.columnDefinition("name", renameDefinition("from"))
			.columnDefinition("family", renameDefinition("to"))
			.forEach(familiesFile, mapping -> families.put(mapping.from, mapping.to));

		for (File file : csvFiles) {
			Matcher matcher = BENCHMARK_FILE.matcher(file.getName());
			boolean matches = matcher.matches();
			String format = matches ? matcher.group(1) != null ? matcher.group(1) : "ans" : "ans";
			Date date = matches ? parseDate(matcher.group(2)) : null;

			MapToDSL<BenchmarkRecord> parser = CsvParser
				.skip(1)
				.mapTo(BenchmarkRecord.class)
				.columnDefinition("Benchmark", identity())
				.columnDefinition("Mode", ignoreDefinition())
				.columnDefinition("Threads", ignoreDefinition())
				.columnDefinition("Samples", ignoreDefinition())
				.columnDefinition("Score", doubleReader())
				.columnDefinition("Score Error (99,9%)", ignoreDefinition())
				.columnDefinition("Unit", ignoreDefinition());

			if (format.contains("a")) {
				parser = parser.columnDefinition("Param: alphabetSize", renameDefinition("alphabet"));
			}
			if (format.contains("n")) {
				parser = parser.columnDefinition("Param: patternNumber", intReader().addRename("number"));
			}
			if (format.contains("s")) {
				parser = parser.columnDefinition("Param: patternSize", renameDefinition("pattern"));
			}

			parser.forEach(file, benchmark -> benchmarks.add(benchmark.withDefaultPatternNumber(1)
				.withDate(date)
				.withAlgorithm(names.getOrDefault(benchmark.getAlgorithm(), benchmark.getAlgorithm()))
				.withType(names.getOrDefault(benchmark.getType(), benchmark.getType()))
				.withFamily(families.get(benchmark.getAlgorithm()))));
		}

		List<BenchmarkCollection> bestscenarios = toCollection(benchmarks.getRecords());

		new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValue(root.resolve("best.json").toFile(),
			bestscenarios);
	}

	private List<BenchmarkCollection> toCollection(List<BenchmarkRecord> records) {
		List<BenchmarkRecord> maxrecords = records.stream()
			.collect(groupingBy(record -> new BenchmarkKey(record.getNumber(), record.getType(),
				record.getPattern(), record.getAlphabet()), maxBy(new BenchmarkScore())))
			.values().stream().map(record -> record.orElse(null)).filter(Objects::nonNull).collect(toList());

		return maxrecords.stream()
			.collect(groupingBy(record -> new CollectionKey(record.getNumber(), record.getType()))).entrySet()
			.stream().map(entry -> new BenchmarkCollection(entry.getKey().number, entry.getKey().type,
				toCandidate(entry.getValue())))
			.sorted()
			.collect(toList());
	}

	private List<Candidate> toCandidate(List<BenchmarkRecord> records) {
		return records.stream().collect(groupingBy(record -> new Algorithm(record.getAlgorithm(), record.getFamily())))
			.entrySet().stream().map(entry -> new Candidate(entry.getKey().algorithm, entry.getKey().family,
				toResult(entry.getValue())))
			.collect(toList());
	}

	private List<Result> toResult(List<BenchmarkRecord> records) {
		return records.stream().map(
			record -> new Result(record.getAlphabet(), record.getPattern(), record.getScore(), record.getDate()))
			.collect(toList());
	}

	public CsvColumnDefinition doubleReader() {
		return customReaderDefinition(new DoubleReader(Double.NaN));
	}

	public CsvColumnDefinition intReader() {
		return customReaderDefinition(new IntReader(0));
	}

	public Date parseDate(String group) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(group);
		} catch (ParseException | NullPointerException e) {
			return null;
		}
	}

	private static class DoubleReader implements CellValueReader<Double> {

		private double defaultValue;

		public DoubleReader(double defaultValue) {
			this.defaultValue = defaultValue;
		}

		@Override
		public Double read(char[] chars, int offset, int length, ParsingContext parsingContext) {
			try {
				return NumberFormat.getInstance(Locale.GERMANY).parse(new String(chars, offset, length)).doubleValue();
			} catch (ParseException e) {
				return defaultValue;
			}
		}

	}

	private static class IntReader implements CellValueReader<Integer> {

		private int defaultValue;

		public IntReader(int defaultValue) {
			this.defaultValue = defaultValue;
		}

		@Override
		public Integer read(char[] chars, int offset, int length, ParsingContext parsingContext) {
			try {
				String str = new String(chars, offset, length);
				return Integer.parseInt(str);
			} catch (NumberFormatException e) {
				return defaultValue;
			}
		}

	}

	private static class Algorithm {
		public String algorithm;
		public String family;

		public Algorithm(String algorithm, String family) {
			this.algorithm = algorithm;
			this.family = family;
		}

		@Override
		public int hashCode() {
			return algorithm.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Algorithm that = (Algorithm) obj;
			return this.algorithm.equals(that.algorithm);
		}

	}

	private static class CollectionKey {
		public int number;
		public String type;

		public CollectionKey(int number, String type) {
			this.number = number;
			this.type = type;
		}

		@Override
		public int hashCode() {
			return number * 13 + type.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			CollectionKey that = (CollectionKey) obj;
			return this.number == that.number && this.type.equals(that.type);
		}

	}

	private static class BenchmarkKey {
		public int number;
		public String type;
		public int pattern;
		public int alphabet;

		public BenchmarkKey(int number, String type, int pattern, int alphabet) {
			this.number = number;
			this.type = type;
			this.pattern = pattern;
			this.alphabet = alphabet;
		}

		@Override
		public int hashCode() {
			return number * 13 + type.hashCode() + pattern * 7 + alphabet * 3;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			BenchmarkKey that = (BenchmarkKey) obj;
			return this.number == that.number && this.type.equals(that.type) && this.pattern == that.pattern
				&& this.alphabet == that.alphabet;
		}

	}

	private static class BenchmarkScore implements Comparator<BenchmarkRecord> {

		@Override
		public int compare(BenchmarkRecord o1, BenchmarkRecord o2) {
			return o1.getScore() > o2.getScore() ? -1 : 1;
		}

	}
}
