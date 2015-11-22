StringBench
===============

Interpretation of the results of 2014-11-22
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
  