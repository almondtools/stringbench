package com.almondtools.stringbench;

import static com.almondtools.stringbenchanalyzer.Family.PREFIX;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;
import org.ahocorasick.trie.Trie.TrieBuilder;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.stringbenchanalyzer.Family;

//excluded from benchmark (moved to tests) until result is correct https://github.com/robert-bor/aho-corasick/issues/29
@State(Scope.Thread)
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
