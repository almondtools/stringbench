package com.almondtools.stringbench.incubation;

import static com.almondtools.stringbenchanalyzer.Family.PREFIX;

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
	public void prepare(Set<String> patterns) {
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

}
