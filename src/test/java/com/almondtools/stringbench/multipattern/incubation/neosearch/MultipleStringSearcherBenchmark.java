package com.almondtools.stringbench.multipattern.incubation.neosearch;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.neosearch.stringsearcher.Algorithm;
import org.neosearch.stringsearcher.Emit;
import org.neosearch.stringsearcher.StringSearcher;
import org.neosearch.stringsearcher.StringSearcherBuilder;

import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmark;

public class MultipleStringSearcherBenchmark extends MultiPatternMatcherBenchmark {

    private static final String ID = "neo-search Aho-Corasick Fast";

    private StringSearcher<String> searcher;

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void preparePatterns(Set<String> patterns) {
        StringSearcherBuilder<String> builder = StringSearcher.<String> builderWithPayload()
            .algorithm(Algorithm.AHO_COHARICK)
            .ignoreOverlaps();
        builder.addSearchStrings(patterns);
        searcher = builder.build();
    }

    @Override
    public List<Integer> find(String text) {
        List<Integer> result = new ArrayList<>();
        for (Emit<String> emit : searcher.parseText(text)) {
            result.add(emit.getStart());
        }
        return result;
    }

    @Override
    public List<Integer> find(File file) throws IOException {
        List<Integer> result = new ArrayList<>();
        String text = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);

        for (Emit<String> emit : searcher.parseText(text)) {
            result.add(emit.getStart());
        }
        return result;
    }

    @Override
    public void free() {
        searcher = null;
    }
}
