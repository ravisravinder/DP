✅ Problem 1: Generate All Subsets of an Array (Power Set)

  import java.util.*;

public class SubsetGenerator {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int index, int[] nums, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(i + 1, nums, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));
    }
}

✅ Problem 2: Subsets with Sum Equal to K
public class SubsetSum {
    public static void findSubsets(int[] arr, int index, int sum, int target, List<Integer> current) {
        if (index == arr.length) {
            if (sum == target) {
                System.out.println(current);
            }
            return;
        }

        // include
        current.add(arr[index]);
        findSubsets(arr, index + 1, sum + arr[index], target, current);
        current.remove(current.size() - 1);

        // exclude
        findSubsets(arr, index + 1, sum, target, current);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        int target = 2;
        findSubsets(arr, 0, 0, target, new ArrayList<>());
    }
}


  
✅ Problem 3: Generate All Subsequences of a String
public class SubsequenceGenerator {
    public static void subsequence(String s, String curr, int index) {
        if (index == s.length()) {
            System.out.println(curr);
            return;
        }

        // include
        subsequence(s, curr + s.charAt(index), index + 1);

        // exclude
        subsequence(s, curr, index + 1);
    }

    public static void main(String[] args) {
        subsequence("abc", "", 0);
    }
}

✅ Problem 4: Count All Subsequences with Sum K
public class CountSubsequences {
    public static int count(int[] arr, int index, int sum, int target) {
        if (index == arr.length) {
            return sum == target ? 1 : 0;
        }

        int include = count(arr, index + 1, sum + arr[index], target);
        int exclude = count(arr, index + 1, sum, target);
        return include + exclude;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        System.out.println("Count: " + count(arr, 0, 0, 2));
    }
}

✅ Problem 5: Subsets with Duplicates
import java.util.*;

public class SubsetsWithDuplicates {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            if (i != start && nums[i] == nums[i - 1]) continue;
            current.add(nums[i]);
            backtrack(i + 1, nums, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        System.out.println(subsetsWithDup(nums));
    }
}

✅ Problem 6: Longest Increasing Subsequence (LIS) — Dynamic Programming
  
public class LongestIncreasingSubsequence {
    public static int lis(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("LIS length: " + lis(nums));
    }
}

  Bonus Challenge: Subsequence Exists in Another String
    
public class IsSubsequence {
    public static boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) i++;
            j++;
        }
        return i == s.length();
    }

    public static void main(String[] args) {
        System.out.println(isSubsequence("abc", "ahbgdc")); // true
    }
}


🔁 Approach: Backtracking (Swapping Technique)

  What is a Permutation?
A permutation is a re-arrangement of elements.
If you have n unique elements, total permutations = n! (n factorial)

So for [1, 2, 3] → 3! = 6 permutations.

  
import java.util.*;

public class Permutations {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        permute(nums, 0, result);
        System.out.println(result);
    }

    // Helper method
    static void permute(int[] nums, int start, List<List<Integer>> result) {
        if (start == nums.length) {
            // Convert array to list and add to result
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            result.add(permutation);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // Swap
            swap(nums, start, i);
            // Recurse
            permute(nums, start + 1, result);
            // Backtrack (undo the swap)
            swap(nums, start, i);
        }
    }

    // Swapping helper
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}




-------------------------
Summary Table
Level	         Problem	                                Concept
Beginner	Generate all subsets	      Recursion + Backtracking
Beginner	Generate all subsequences	Recursion
Intermediate	Subsets with target sum	Recursion + Backtrack
Intermediate	Count subsequences with sum	Recursion
Advanced	Subsets with duplicates	Recursion + Sorting
Advanced	Longest Increasing Subsequence	Dynamic Programming
Advanced	Is one string subsequence of another	Two pointers
