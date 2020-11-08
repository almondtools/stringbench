package com.almondtools.stringbench.multipattern.hankcs;

import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmark;
import com.almondtools.stringbench.multipattern.MultiPatternMatcherBenchmarkTest;

public class HankcsAhoCorasickBenchmarkTest extends MultiPatternMatcherBenchmarkTest {

    @Override
    protected MultiPatternMatcherBenchmark getBenchmark() {
        return new HankcsAhoCorasickBenchmark();
    }


}
