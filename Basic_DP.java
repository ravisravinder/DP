1 .public class FibonacciMemoizationArray {

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

===========================  
