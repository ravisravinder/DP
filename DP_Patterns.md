Think of DP as:
“Storing answers to small problems to solve bigger ones faster.”

🚩 Core Concepts (Beginner-Friendly)
Concept	Explanation	Java Data Structure Used
Memoization (Top-Down)	Recursive + cache the result	HashMap or array
Tabulation (Bottom-Up)	Build from base case	1D or 2D array
State	What defines your subproblem	Index, weight, total, etc.
Choice Diagram	Choose, skip, include, move left/right/up/down	Helpful in forming recurrence

🧩 DP Patterns (With Java Hints)
1. 0/1 Knapsack Pattern
Choose or Don’t Choose an item.

Every item used at most once.

Uses 2D DP → dp[i][w] = max profit with first i items and capacity w

java
Copy
Edit
for (int i = 1; i <= n; i++) {
    for (int w = 0; w <= capacity; w++) {
        if (weights[i-1] <= w) {
            dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w - weights[i-1]] + values[i-1]);
        } else {
            dp[i][w] = dp[i-1][w];
        }
    }
}
2. Unbounded Knapsack Pattern
Choose or Not, but can reuse items unlimited times.

Useful for coin change, rod cutting.

Uses 1D DP or 2D DP depending on problem.

java
Copy
Edit
for (int i = 0; i < n; i++) {
    for (int w = weights[i]; w <= capacity; w++) {
        dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
    }
}
3. Fibonacci Pattern
Each state depends on previous 1–2 states.

Problems: House Robber, Climb Stairs, Jump Game, etc.

1D DP (or just 2 variables for space optimization)

java
Copy
Edit
int[] dp = new int[n+1];
dp[0] = 0;
dp[1] = 1;
for (int i = 2; i <= n; i++) {
    dp[i] = dp[i-1] + dp[i-2];
}
4. Grid/Matrix DP (Unique Paths I/II)
Movement in grid (down/right), avoid obstacles.

Use 2D DP.

java
Copy
Edit
for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
        if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
        else if (i == 0 && j == 0) dp[i][j] = 1;
        else dp[i][j] = (i > 0 ? dp[i-1][j] : 0) + (j > 0 ? dp[i][j-1] : 0);
    }
}
5. Longest Common Subsequence (LCS) Pattern
Match or move.

Used in: LCS, Edit Distance, Longest Palindromic Subseq

2D DP: dp[i][j] = length of LCS of s1[0..i] and s2[0..j]

java
Copy
Edit
if (s1.charAt(i-1) == s2.charAt(j-1)) {
    dp[i][j] = 1 + dp[i-1][j-1];
} else {
    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
}
🧠 When to use 1D vs 2D DP:
Case	Use 1D DP	Use 2D DP
Depends only on previous value(s)	✅	❌
States depend on two changing parameters (like i and j)	❌	✅
Can be optimized for space	✅	❌ initially

✅ Checklist of All Must-Know DP Problems
Category	Problem Example
✅ Fibonacci	Climb Stairs, Jump Game
✅ House Robber	Max sum without adjacent
✅ Knapsack 0/1	Subset Sum, Target Sum
✅ Unbounded Knapsack	Coin Change, Rod Cutting
✅ Grid Problems	Unique Paths I/II, Min Path Sum
✅ Subsequence	LCS, Longest Palindrome Subseq
✅ Substring	Longest Palindromic Substring
✅ String Edit	Edit Distance, Wildcard Match
✅ Partition	Equal Subset Sum, Palindrome Partitioning
✅ LIS / LDS	Longest Increasing Subsequence
✅ Bitmasking (Advanced)	Travelling Salesman Problem

📘 Reference (Clean Java Docs & Practice)
Here are curated beginner-friendly resources:

Leetcode Patterns Sheet (by Sean Prashad)

Java DP Patterns Explained - GeeksForGeeks

NeetCode’s Dynamic Programming Playlist (YouTube) - Clear, pattern-focused.

Tech Interview Handbook - DP Patterns
