StringBench
===============
This project compares different java string matching implementations with a performance test.

Benchmark quality
-----------------
* We use jmh benchmarking.
* The benchmark is not (yet) tested on a clean machine
* The benchmark has few iterations (making its results less significant)

Subject of this Benchmark
-------------------------
The main subject of this benchmark is comparing different algorithms - not different implementations.

Yet a wrong (or suboptimal) implementation is easier identified if compared with other implementations (that deviate strongly in performance).

Minor deviations between the performance of different implementations of the same algorithm should be ignored
* some are caused by the adaptor benchmark code
* some frameworks use a sophisticated API and therefore do some more work

Use this benchmark only to select the algorithm of your choice and then select the implementation that is most suiting your requirements.  

Participants
------------
[Pattern.compile/Matcher.find,String.indexOf (SDK)](https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html)
[StringsAndChars (SC)](https://github.com/almondtools/stringsandchars)
[StringSearch (SS)](http://johannburkard.de/software/stringsearch/)
[AhoCorasick (AC)](https://github.com/robert-bor/aho-corasick)

Participating
-------------
If you want another framework participating in this benchmarks, meet following conditions:
* the framework must be available in a public maven repository
* provide a Benchmark implementation (extending `SinglePatternMatcherBenchmark` or `MultiPatternMatcherBenchmark`)
* the benchmarked algorithms have to pass the tests without deviations 


Interpretation of the [results of 2015-11-22](result-2015-11-22)
-------------------------------------------
* Participating: SDK, SC, SS
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
  
Interpretation of the [results of 2015-12-06](result-2015-12-06)
-------------------------------------------
* Participating: SDK, SC, SS, AC
* Single Pattern
  * Simple `String.indexOf` dominates the region of small patterns with small alphabet
  * SC ShiftAnd performs good for small alphabet size and smaller patterns
  * SC BNDM performs good for small alphabet size and larger patterns
  * SC Horspool/`Pattern.compile/Matcher.find` work best for middle alphabet size and smaller patterns
  * SC Horspool/SC Sunday and `Pattern.compile/Matcher.find` cover the region of large alphabets and large patterns
* Multi Pattern
  * Simple `String.indexOf` dominates the region of few patterns of small alphabet and size
  * SC SetHorspool performs best for few patterns of large alphabet and size
  * SC WuManber performs best for long patterns with small alphabets
  * AC AhoCorasick performs best for many small patterns with large alphabets
  * SC SetBackwardOracleMatching performs best for many long patterns with large alphabets
  * It seems that SC AhoCorasick and AC AhoCorasick do not implement the same algorithm (their performance charasteristics differ) 
  