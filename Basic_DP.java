1 .public class FibonacciMemoizationArray {  //TopDOwn ===Recursion ==Memoization

    public static int fibonacci(int n, int[] memo) {
        if (n <= 1) return n;
        if (memo[n] != -1)
          return memo[n];
        memo[n] = fibonacci(n - 1, memo) + fibonacci(n - 2, memo);
        return memo[n];
    }

   public static void main(String[] args) {
        int count = 5;
        int[] memo = new int[count + 1];
        Arrays.fill(memo, -1); 
        for (int i = 0; i < count; i++) {
            System.out.print(fibonacci(i, memo) + " ");
        }
    }
}

// Time Complexity	O(n)
// Space Complexity	O(n)
========================================
import java.util.HashMap;
import java.util.Map;

public class FibonacciMemoization {

    private static Map<Integer, Integer> memo = new HashMap<>();

    public static int fibonacci(int n) {
        if (n <= 1) 
            return n;

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int result = fibonacci(n - 1) + fibonacci(n - 2);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        int count = 5;
        for (int i = 0; i < count; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }
}

// Time Complexity	O(n)
// Space Complexity	O(n)  [O(n) for recurstion+o(n) for array/map]
===========================  

   //Tabulation

    public class FibonacciTabulation {

    public static int fibonacci(int n) {
        if (n <= 1) return n;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int count = 5;
        for (int i = 0; i < count; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }
}

TC: O(n)
SC: o(n)    
=================================================================

    Optimized

    public class FibonacciOptimized {

    public static int fibonacci(int n) {
        if (n <= 1) return n;

        int prev2 = 0; // Fibonacci(0)
        int prev1 = 1; // Fibonacci(1)
        int current = 0;

        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }

    public static void main(String[] args) {
        int count = 5;
        for (int i = 0; i < count; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }
}

===============================================TUF-1 end-------------------------------------------------------------------------
    
    
! The "Count ways to reach Nth stair" problem is a classic dynamic programming problem. The idea is:

You can move either 1 step or 2 steps at a time.

Find how many distinct ways to reach the Nth stair.


public class StaircaseWays {

    public static int countWays(int n) {
        // dp[i] = number of ways to reach the ith stair

        int[] dp = new int[n + 1];

        // Base cases
        dp[0] = 1;  // 1 way to stand at the ground (step 0)
        if (n >= 1) dp[1] = 1;  // 1 way to reach step 1 (single step)

        // Fill the dp array for stairs 2 to n
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];  // sum of ways to previous two steps
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 5; // example input
        System.out.println("Number of ways to reach stair " + n + ": " + countWays(n));
    }
}


===========================================
    public static int countWaysOptimized(int n) {
    if (n == 0 || n == 1) return 1;

    int prev1 = 1; // dp[i - 1]
    int prev2 = 1; // dp[i - 2]

    for (int i = 2; i <= n; i++) {
        int current = prev1 + prev2;
        prev2 = prev1;
        prev1 = current;
    }

    return prev1;
}

