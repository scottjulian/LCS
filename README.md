#LCS
Finding the **Longest Common Subsequence** in a Directed Acyclic Graph written in Java

### Run Tests
```
./gradlew test
```

### Example
Given Sequences:
```
abcde
bcaed
adbce
```
Output:
```
all pairs:
  (a b) (a c) (a d) (a e) (b c) (b d) (b e) (c d) (c e) (d e)
  (b c) (b a) (b e) (b d) (c a) (c e) (c d) (a e) (a d) (e d)
  (a d) (a b) (a c) (a e) (d b) (d c) (d e) (b c) (b e) (c e)

intersected pairs:
  (a d) (a e) (b c) (b e) (c e)

topological order of nodes:
  abdec

longest common subsequence:
  b c e
```

### Read the blog post
http://blog.scottjulian.net/2015/03/19/finding-the-longest-common-subsequence/
