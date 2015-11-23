StringBench
===============
This project compares different java string matching implementations with a performance test.

Promoting another string matching library
-----------------------------------------
If you know a framework which could compete, please ensure that following conditions are met:
* only libraries available as maven artifact can participate
* to connect the benchmark to the new library, we will need an adaptor class. Integrating will go easier if you already provide this adaptor class as pull request
* each integrated algorithm (for single pattern or multi pattern search) is tested together with the other existing algorithms. The benchmark will only be integrated if the tests do not deviate.


Interpretation of the results of 2015-11-22
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
  