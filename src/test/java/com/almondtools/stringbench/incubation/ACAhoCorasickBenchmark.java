package com.almondtools.stringbench.incubation;

import static com.almondtools.stringbenchanalyzer.Family.PREFIX;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;
import org.ahocorasick.trie.Trie.TrieBuilder;

import com.almondtools.stringbench.MultiPatternMatcherBenchmark;
import com.almondtools.stringbenchanalyzer.Family;

public class ACAhoCorasickBenchmark extends MultiPatternMatcherBenchmark {

	private static final String ID = "AhoCorasick.org Aho-Corasick";

	private Trie trie;

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Family getFamily() {
		return PREFIX;
	}

	@Override
	public void preparePatterns(Set<String> patterns) {
		TrieBuilder builder = Trie.builder().removeOverlaps();
		for (String pattern : patterns) {
			builder.addKeyword(pattern);
		}
		trie = builder.build();
	}

	@Override
	public List<Integer> find(String text) {
		List<Integer> result = new ArrayList<>();
		for (Emit emit : trie.parseText(text)) {
			result.add(emit.getStart());
		}
		return result;
	}

	@Override
	public List<Integer> find(File file) throws IOException {
		List<Integer> result = new ArrayList<>();
		String text = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);

		for (Emit emit : trie.parseText(text)) {
			result.add(emit.getStart());
		}
		return result;
	}

}
