package com.almondtools.stringbench.analyzer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

public class SampleGenerator {
	public static void main(String[] args) throws IOException {
		String bytes = new String(Files.readAllBytes(Paths.get("src/main/resources/sample-kjb.sample")), StandardCharsets.UTF_8);
		List<String> tokens = tokens(Files.readAllLines(Paths.get("src/main/resources/sample-kjb.sample")));
		
		try (BufferedWriter w = Files.newBufferedWriter(Paths.get("src/main/resources/pattern-kjb.result"))) {
			Random r = new Random();
			Set<String> done = new HashSet<>();
			for (int i =0; i < 20000; i++) {
				String pattern = generatePattern(r, tokens, done);

				w.write(pattern + ":" + findAll(bytes, pattern));
				w.newLine();
				if (i % 1000 == 0) {
					System.out.println(i);
				}
			}
		}
	}

	private static List<String> tokens(List<String> lines) {
		Set<String> tokens = new LinkedHashSet<>();
		for (String line : lines) {
			StringTokenizer tokenizer = new StringTokenizer(line, ".;:?!,()\"'");
			while (tokenizer.hasMoreTokens()) {
				tokens.add(tokenizer.nextToken());
			}
		}
		System.out.println(tokens.size());
		return new ArrayList<>(tokens);
	}

	private static String generatePattern(Random r, List<String> tokens, Set<String> done) {
		int index = r.nextInt(tokens.size());
		String token = tokens.get(index).trim();
		while (done.contains(token)) {
			index = r.nextInt(tokens.size());
			token = tokens.get(index).trim();
		}
		done.add(token);
		return token;
	}

	public static String computePattern(String bytes, Random r) {
		int rnd = r.nextInt(bytes.length());
		int len = 5 + r.nextInt(5);
		return bytes.substring(rnd, rnd+len);
	}

	private static int findAll(String bytes, String pattern) {
		int occ = 0;
		int start = 0;
		while (true) {
			int pos = bytes.indexOf(pattern, start);
			if (pos < 0) {
				break;
			}
			occ++;
			start = pos + 1;
		}
		return occ;
	}
}
