package com.almondtools.stringbenchanalyzer;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.minBy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ProcessResults {

	private String file;
	private Map<Family, Integer> registeredNumber;
	private Map<String, String> registeredColors;

	public ProcessResults(String file) {
		this.file = file;
		this.registeredNumber = new EnumMap<Family, Integer>(Family.class);
		this.registeredColors = new HashMap<String, String>();
	}

	public static void main(String[] args) throws IOException {
		ProcessResults processResults = new ProcessResults(args[0]);
		processResults.run();
	}

	public void run() throws IOException {
		Map<Integer, Map<String, Optional<BenchmarkRecord>>> grouped = allRecords().stream()
			.collect(groupingBy(BenchmarkRecord::getNumber, groupingBy(BenchmarkRecord::getKey, minBy(benchmarkOrder()))));
		for (Map.Entry<Integer, Map<String, Optional<BenchmarkRecord>>> entry : grouped.entrySet()) {
			Integer key = entry.getKey();
			Map<String, Optional<BenchmarkRecord>> benchmarks = entry.getValue();
			writeSCSV(key, benchmarks);
			writeHighChart(key, benchmarks);
		}

	}

	private void writeSCSV(Integer key, Map<String, Optional<BenchmarkRecord>> benchmarks) throws IOException {
		String name = file.substring(0, file.length() - 4) + "_" + key + ".csv";
		Files.write(Paths.get(name), new Iterable<String>() {

			@Override
			public Iterator<String> iterator() {
				return benchmarks.values().stream()
					.flatMap(opt -> opt.map(value -> Stream.of(value)).orElse(noBenchmarks()))
					.sorted(comparing(BenchmarkRecord::getAlphabet).thenComparing(BenchmarkRecord::getPatternSize))
					.map(value -> value.coordinates())
					.iterator();
			}

		}, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
	}

	private void writeHighChart(Integer key, Map<String, Optional<BenchmarkRecord>> benchmarks) throws IOException {
		String name = file.substring(0, file.length() - 4) + "_" + key + ".html";
		String templateName = "benchmarkresults/result.html";
		String template = Files.readAllLines(Paths.get(templateName)).stream().collect(joining("\n"));

		String title = key <= 1 ? "Exact String Matching for single Patterns" : "Exact String Matching for multiple Patterns";

		Map<String, List<BenchmarkRecord>> grouped = benchmarks.values().stream()
			.filter(Optional::isPresent)
			.map(Optional::get)
			.collect(groupingBy(BenchmarkRecord::getNameAndFamily));

		String data = grouped.entrySet().stream()
			.map(entry -> createHighChartData(entry.getValue()))
			.collect(joining(","));

		String content = template.replace("${title}", title).replace("${data}", data);

		Files.write(Paths.get(name), asList(content), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

	}

	private String createHighChartData(List<BenchmarkRecord> value) {

		String name = value.stream().map(BenchmarkRecord::getName).findFirst().orElse("unknown");
		Family family = value.stream().map(BenchmarkRecord::getFamily).findFirst().orElse(Family.SPECIAL);

		StringBuilder buffer = new StringBuilder();
		buffer.append('{');

		buffer.append("name: '").append(name).append("',");
		buffer.append("marker: {")
			.append("symbol: '").append(shapeFor(family)).append("'")
			.append("},");
		buffer.append("color: '").append(colorFor(name, family)).append("',");
		buffer.append("data: [").append(dataFor(value)).append("]");
		buffer.append('}');
		return buffer.toString();
	}

	private String shapeFor(Family family) {
		switch (family) {
		case NAIVE:
			return "square";
		case PREFIX:
			return "triangle";
		case SUFFIX:
			return "triangle-down";
		case FACTOR:
			return "diamond";
		default:
			return "circle";
		}
	}

	private String colorFor(String name, Family family) {
		return registeredColors.computeIfAbsent(name, key -> newColorFor(key, family));
	}

	private String newColorFor(String name, Family family) {
		int number = registeredNumber.compute(family, (k, v) -> v == null ? 0 : v + 1);
		if (number == 0) {
			return baseColor(family);
		} else {
			return color(family, number);
		}
	}

	private String baseColor(Family family) {
		switch (family) {
		case NAIVE:
			return rgba(256, 256, 0);
		case PREFIX:
			return rgba(0, 256, 0);
		case SUFFIX:
			return rgba(0, 256, 256);
		case FACTOR:
			return rgba(0, 0, 256);
		case HASHING:
			return rgba(256, 0, 256);
		default:
			return rgba(256, 0, 0);
		}
	}

	private String color(Family family, int number) {
		int r = number % 2 == 0 ? (128 >> (number - 1)) + 32 : 0;
		int l = number % 2 == 0 ? 0 : (128 >> number) + 32;

		switch (family) {
		case NAIVE:
			return rgba(256 - r, 256 - l, 0);
		case PREFIX:
			return rgba(r, 256, l);
		case SUFFIX:
			return rgba(0, 256 - r, 256 - l);
		case FACTOR:
			return rgba(r, l, 256);
		case HASHING:
			return rgba(256 - r, 0, 256 - l);
		default:
			return rgba(256, r, l);
		}
	}

	private String rgba(int r, int g, int b) {
		return "rgba(" + r + "," + g + "," + b + ",256)";
	}

	private String dataFor(List<BenchmarkRecord> records) {
		return records.stream()
			.map(record -> "[" + record.getPatternSize() + ',' + record.getAlphabet() + "]")
			.collect(joining(","));
	}

	public Stream<BenchmarkRecord> noBenchmarks() {
		return Stream.<BenchmarkRecord> empty();
	}

	public Comparator<BenchmarkRecord> benchmarkOrder() {
		return naturalOrder();
	}

	public List<BenchmarkRecord> allRecords() throws IOException {
		List<BenchmarkRecord> records = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(file), Charset.forName("UTF-8"))) {
			skipFirstLine(reader);
			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				StreamTokenizer tokens = tokenizer(line);

				String benchmark = stringLiteral(tokens);

				String name = name(benchmark);
				Family family = family(benchmark);

				skip(tokens, 7);

				double time = time(stringLiteral(tokens));

				skip(tokens, 5);

				int alphabet = intLiteral(tokens);

				skip(tokens);

				int patternNumber = optIntLiteral(tokens).orElse(1);

				skip(tokens);

				int patternSize = intLiteral(tokens);

				records.add(new BenchmarkRecord(patternNumber, alphabet, patternSize, time, name, family));

			}
		}
		return records;
	}

	public void skip(StreamTokenizer tokens, int times) throws IOException {
		for (int i = 0; i < times; i++) {
			skip(tokens);
		}
	}

	public void skip(StreamTokenizer tokens) throws IOException {
		tokens.nextToken();
	}

	public String stringLiteral(StreamTokenizer tokens) throws IOException {
		int type = tokens.nextToken();
		if (type != '\"') {
			throw new RuntimeException();
		}
		return tokens.sval;
	}

	public int intLiteral(StreamTokenizer tokens) throws IOException {
		int type = tokens.nextToken();
		if (type != StreamTokenizer.TT_NUMBER) {
			throw new RuntimeException();
		}
		return (int) tokens.nval;
	}

	public Optional<Integer> optIntLiteral(StreamTokenizer tokens) throws IOException {
		int type = tokens.nextToken();
		if (type == ',') {
			tokens.pushBack();
			return Optional.empty();
		} else if (type != StreamTokenizer.TT_NUMBER) {
			throw new RuntimeException();
		}
		return Optional.of((int) tokens.nval);
	}

	public StreamTokenizer tokenizer(String line) {
		StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(line));
		tokenizer.quoteChar('"');
		return tokenizer;
	}

	private String name(String string) {
		try {
			String[] parts = string.substring(1, string.length() - 1).split("\\.");
			return parts[parts.length - 2].replace("Benchmark", "");
		} catch (NullPointerException | NumberFormatException e) {
			return "<unknown>";
		}
	}

	private Family family(String string) {
		try {
			int pos = string.lastIndexOf('.');
			String className = string.substring(0, pos);
			Class<?> clazz = Class.forName(className);
			Method familyMethod = clazz.getMethod("getFamily");
			return (Family) familyMethod.invoke(clazz.newInstance());
		} catch (NullPointerException | ReflectiveOperationException e) {
			return Family.SPECIAL;
		}
	}

	private double time(String string) {
		try {
			return NumberFormat.getNumberInstance().parse(string).doubleValue();
		} catch (NullPointerException | NumberFormatException | ParseException e) {
			return Double.NaN;
		}
	}

	public void skipFirstLine(BufferedReader reader) throws IOException {
		reader.readLine();
	}

}
