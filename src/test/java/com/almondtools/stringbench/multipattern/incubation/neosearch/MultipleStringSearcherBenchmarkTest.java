package com.almondtools.stringbench.multipattern.incubation.neosearch;

import org.junit.Rule;
import org.junit.Test;

import com.almondtools.stringbench.CompareResultNotAccepted;
import com.almondtools.stringbench.StringBenchIncubation;
import com.almondtools.stringbench.multipattern.MultiPatternSample;
import com.almondtools.stringbench.multipattern.MultiPatternTest;

public class MultipleStringSearcherBenchmarkTest extends MultiPatternTest {

    @Rule
    public StringBenchIncubation incubation = new StringBenchIncubation(new MultipleStringSearcherBenchmark());
    
    @Rule
    public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();
    
    @Test
    public void test222() throws Exception {
        MultiPatternSample sample = createSample(2, 2, 2);
        incubation.benchmarkFindInString(sample);
    }

}
