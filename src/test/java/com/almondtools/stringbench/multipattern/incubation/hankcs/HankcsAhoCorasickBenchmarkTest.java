package com.almondtools.stringbench.multipattern.incubation.hankcs;

import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmark;
import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmarkTest;
import com.almondtools.stringbench.multipattern.hankcs.HankcsAhoCorasickBenchmark;

public class HankcsAhoCorasickBenchmarkTest extends MultiPatternMatcherBenchmarkTest {

    @Override
    protected MultiPatternMatcherBenchmark getBenchmark() {
        return new HankcsAhoCorasickBenchmark();
    }


}
