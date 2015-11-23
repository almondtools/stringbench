StringBench
===============
This project compares different java string matching implementations with a performance test.

Benchmark quality
-----------------
* We use jmh benchmarking.
* The benchmark is not (yet) tested on a clean machine
* The benchmark has few iterations (making its results less significant)

Participants
------------
[StringsAndChars (SC)](https://github.com/almondtools/stringsandchars)
[StringSearch (SS)](http://johannburkard.de/software/stringsearch/)

Participating
-------------
If you want another framework participating in this benchmarks, meet following conditions:
* the framework must be available in a public maven repository
* provide a Benchmark implementation (extending `SinglePatternMatcherBenchmark` or `MultiPatternMatcherBenchmark`)
* the benchmarked algorithms have to pass the tests without deviations 


Interpretation of the [results of 2015-11-22](result-2015-11-22)
-------------------------------------------

* Single Pattern
  * `Pattern.compile/Matcher.find` works fine for small alphabets and small patterns
  * SS BNDM is the best for small alphabets and longer patterns
  * SC Horspool is best for large alphabets and longer patterns
  * differences are minimal
* Multi Pattern
  * SC AhoCorasick performs best for small patterns and small alphabets
  * SC WuManber performs best for long patterns with small alphabets
  * SC SetBackwardOracleMatching performs best for long patterns with middle sized alphabets
  * SC SetHorspool performs best for long patterns with large alphabets
  