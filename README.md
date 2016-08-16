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

An Overview of libraries
------------------------

###[Java API](http://docs.oracle.com/javase/7/docs/api)
Java provides two ways to search for strings:
- naive search with [String.indexOf](http://docs.oracle.com/javase/7/docs/api/java/lang/String.html)
- Boyer-Moore algorithm with [Pattern.compile/Matcher.find](https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html)

Both algorithms are very stable, passing all benchmarks. The naive algorithm will not perform well on large texts/patterns. The boyer-moore is actively challenged by other implementations below. 

### <a name="stringsandchars"></a>[StringsAndChars (SC)](https://github.com/almondtools/stringsandchars)
My own library StringAndChars provides many algorithms for single and multiple patterns along with some experimental features (e.g. regex search), providing the algorithms:

- BNDM
- BOM
- Horspool
- Knuth-Morris-Pratt
- Sunday
- Aho-Corasick- 
- Set Backward Oracle Matching
- Set Horspool
- Wu-Manber

I am continuously improving the design and trying to keep the test coverage near 100%. It is actively maintained and passes all tests of the benchmark.
 
### <a name="byteseek"></a>[ByteSeek (BS)](https://github.com/nishihatapalmer/byteseek)
byteseek is a library for efficiently mathcing patterns of bytes and search for those patterns, providing the algorithms:

- Horspool
- Sunday
- Set Horspool
- Wu-Manber

byteseek provides a well designed api, is actively maintained and passes all tests of the benchmark.

### <a name="stringsearch"></a>[StringSearch (SS)](http://johannburkard.de/software/stringsearch/)
StringSearch is a very popular string searching library for single pattern search (and also wildcard search). It does not pass the benchmark tests and is therefore excluded from the benchmark. Yet waiting for a [maintainer statement](https://github.com/johannburkard/StringSearch/issues/4).
 
### <a name="aho-corasick"></a>[AhoCorasick (AC)](https://github.com/robert-bor/aho-corasick)
AhoCorasick is a popular one-algorithm library that implements the Aho-Corasick algorithm. It does not pass the benchmark tests and is therefore excluded from the benchmark. Yet waiting for a [maintainer statement](https://github.com/robert-bor/aho-corasick/issues/36).
 
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
---------------------------------------------
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
---------------------------------------------
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

Interpretation of the [results of 2016-08-13](benchmarkresults/result-2016-08-13.txt?raw=true)
---------------------------------------------
* Participating: SDK, SC, BS
* Excluded: AC, SS (api problems)
* The benchmark has a bias towards `String.indexOf` (is reported as issue)
* The new benchmark byteseek looks very slow, but we found out that the main performance overhead was produced in the test setup
* The main picture is for Single Pattern:
  * small patterns or long alphabets => SDK indexOf
  * small alphabets/small pattern size => SC ShiftAnd
  * small alphabets/medium pattern size => SC BNDM
  * small alphabets/large pattern size => SC BOM
  * the region between => Boyer-Moore variants (SC Sunday, SC Horspool, SDK Regex)
* for Multiple Patterns:
  * small patterns and small alphabets = SC Aho-Corasick
  * few small patterns and large alphabets = SC Set Horspool
  * few medium patterns and medium alphabets = SC Wu-Manber
  * many large patterns and large alphabets = SC Set BOM
  
 
