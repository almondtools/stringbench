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
* [Pattern.compile/Matcher.find](https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html), [String.indexOf](http://docs.oracle.com/javase/7/docs/api/java/lang/String.html) (SDK)
* [StringsAndChars (SC)](https://github.com/almondtools/stringsandchars)
* [StringSearch (SS)](http://johannburkard.de/software/stringsearch/)
* [AhoCorasick (AC)](https://github.com/robert-bor/aho-corasick)

Participating
-------------
If you want another framework participating in this benchmarks, meet following conditions:
* the framework must be available in a public maven repository
* provide a Benchmark implementation (extending `SinglePatternMatcherBenchmark` or `MultiPatternMatcherBenchmark`)
* the benchmarked algorithms have to pass the tests without deviations 


Interpretation of the [results of 2015-11-22](benchmarkresults/result-2015-11-22.txt?raw=true)
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
  
Interpretation of the [results of 2015-12-06](benchmarkresults/result-2015-12-06.txt?raw=true)
-------------------------------------------
* Participating: SDK, SC, SS, AC
* (AC) AhoCorassic is remarkably fast with many patterns, but in this setup it is not reliable: The results for patterns/samples deviate from those returned by other algorithms (even the naive brute force ones). Therefore it will be excluded from the benchmark until the source of this deviation is clarified
* It gets obvious that the benchmark setup is not fair: Patterns are generated as substrings from the sample, which ensures that at least one match could be found. Brute force algorithms based on backtracking will match this pattern very quick. In scenarios with large alphabets such brute force algorithms will probably backtrack only small distance. Real random data would probably penalize brute force algorithms compared to the current setup.
* Single Pattern
  * (SDK) Simple `String.indexOf` dominates the region of small patterns with small alphabet
  * (SC) ShiftAnd performs good for small alphabet size and smaller patterns
  * (SC) BNDM performs good for small alphabet size and larger patterns
  * (SC) Horspool/(SC) Sunday/(SDK) `Pattern.compile/Matcher.find` cover the region of large alphabets and large patterns
* Multi Pattern
  * (SDK) Simple `String.indexOf` dominates the region of few patterns of small pattern size
  * (SC) SetHorspool performs best for few patterns of large alphabet and size
  * (SC) AhoCorasick performs best for more patterns of small alphabet and size
  * (SC) WuManber performs best for more patterns of medium alphabet and size 
  * (SC) SetBackwardOracleMatching performs best for more patterns with large alphabets
  
Interpretation of the [results of 2015-12-13](benchmarkresults/result-2015-12-13.txt?raw=true)
-------------------------------------------
* Participating: SDK, SC, SS
* The benchmark is yet not optimal. It does search all non overlapping matches in a text. This makes it easier for the naive search, because it can skip the search to the point after the last match. The other algorithms collect all matches and filter out the overlapping ones. Certainly this is a good reason to optimize the non-overlapping matching algorithms. Yet it is also a reason to optimize the benchmark that each algorithm is also tested for overlapping matches.   
* Single Pattern
  * (SDK) Simple `String.indexOf` dominates the region of small patterns with small alphabet
  * (SC) ShiftAnd performs good for small alphabet size and smaller patterns
  * (SC) BNDM performs good for small alphabet size and larger patterns
  * (SC) Horspool/(SC) Sunday/(SDK) `Pattern.compile/Matcher.find` cover the region of large alphabets and large patterns
* Multi Pattern
  * (SDK) Simple `String.indexOf` dominates the region of few patterns of small pattern size
  * (SC) SetHorspool performs good for few patterns of large alphabet
  * (SC) AhoCorasick performs good for more patterns of small pattern size
  * (SC) WuManber performs good for more patterns of medium alphabet and size 
  * (SC) SetBackwardOracleMatching performs best for more patterns with large pattern size
  