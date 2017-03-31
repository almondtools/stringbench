package com.almondtools.stringbench.multipattern.hankcs;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmark;
import com.hankcs.algorithm.AhoCorasickDoubleArrayTrie;
import com.hankcs.algorithm.AhoCorasickDoubleArrayTrie.IHit;

public class HankcsAhoCorasickBenchmark extends MultiPatternMatcherBenchmark {

    private static final String ID = "AhoCorasickDoubleArrayTrie Aho-Corasick";

    private AhoCorasickDoubleArrayTrie<String> trie;

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void preparePatterns(Set<String> patterns) {
        trie = new AhoCorasickDoubleArrayTrie<String>();
        Map<String, String> map = new HashMap<>();
        for (String pattern : patterns) {
            map.put(pattern, pattern);
        }
        trie.build(map);
    }

    @Override
    public List<Integer> find(String text) {
        List<Integer> result = new ArrayList<>();
        trie.parseText(text, new Processor(result));
        return result;
    }

    @Override
    public List<Integer> find(File file) throws IOException {
        List<Integer> result = new ArrayList<>();
        String text = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        trie.parseText(text, new Processor(result));
        return result;
    }

    @Override
    public void free() {
        trie = null;
    }
    
    private static class Processor implements IHit<String> {

        private List<Integer> result;
        private int next = 0;
        
        public Processor(List<Integer> result) {
            this.result = result;
        }

        @Override
        public void hit(int begin, int end, String value) {
            if (begin >= next) {
                result.add(begin);
                next = begin + value.length();
            }
        }
    }
}
