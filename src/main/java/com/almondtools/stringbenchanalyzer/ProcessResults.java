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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ProcessResults {

	private String file;

	public ProcessResults(String file) {
		this.file = file;
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
			String csv = file.substring(0, file.length() - 4) + "_" + key + ".csv";
			String html = file.substring(0, file.length() - 4) + "_" + key + ".html";
			writeSCSV(key, benchmarks, csv);
			writeChart(key, html, csv);
		}

	}

	private void writeSCSV(Integer key, Map<String, Optional<BenchmarkRecord>> benchmarks, String filename) throws IOException {
		Files.write(Paths.get(filename), new Iterable<String>() {

			@Override
			public Iterator<String> iterator() {
				return Stream.concat(Stream.of("name,family,alphabet,pattern"), benchmarks.values().stream()
					.flatMap(opt -> opt.map(value -> Stream.of(value)).orElse(noBenchmarks()))
					.sorted(comparing(BenchmarkRecord::getAlphabet).thenComparing(BenchmarkRecord::getPatternSize))
					.map(value -> value.coordinates()))
					.iterator();
			}

		}, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
	}

	private void writeChart(Integer key, String name, String csv) throws IOException {
		String fileName = csv.substring("benchmarkresults/".length());
		
		String templateName = "benchmarkresults/result.html";
		String template = Files.readAllLines(Paths.get(templateName)).stream().collect(joining("\n"));

		String title = key <= 1 ? "Exact String Matching for single Patterns" : "Exact String Matching for multiple Patterns";

		String content = template.replace("${title}", title).replace("${file}", fileName);

		Files.write(Paths.get(name), asList(content), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

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
