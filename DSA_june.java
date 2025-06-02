1. Two Sum (Pattern: HashMap / Two Pointers)

  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int diff = target - nums[i];
        if (map.containsKey(diff)) {
            return new int[] { map.get(diff), i };
        }
        map.put(nums[i], i);
    }
    throw new IllegalArgumentException("No two sum solution");
}


Input: nums = [2,7,11,15], target = 9  
Output: [0,1] because nums[0] + nums[1] = 2 + 7 = 9

----------

2.Longest Substring Without Repeating Characters (Pattern: Sliding Window)

Input: "abcabcbb"  
Output: 3 → ("abc")


  public int lengthOfLongestSubstring(String s) {
    Set<Character> set = new HashSet<>();
    int left = 0, right = 0, maxLen = 0;

    while (right < s.length()) {
        if (!set.contains(s.charAt(right))) {
            set.add(s.charAt(right++));
            maxLen = Math.max(maxLen, set.size());
        } else {
            set.remove(s.charAt(left++));
        }
    }
    return maxLen;
}

-----------------------------------
  3. Merge Intervals (Pattern: Sorting + Merge)

Input: [[1,3],[2,6],[8,10],[15,18]]  
Output: [[1,6],[8,10],[15,18]]

  
public int[][] merge(int[][] intervals) {
    // Edge case: if only one or no interval, return as is.
    if (intervals.length <= 1) return intervals;

    // Step 1: Sort the intervals based on the start value.
    // Example:
    // Before sorting -> [[8,10], [1,3], [15,18], [2,6]]
    // After sorting  -> [[1,3], [2,6], [8,10], [15,18]]
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));   
  //a -> a[0] means: sort using the 0th element (the start time) of each interval.

//Arrays.sort(intervals, Comparator.comparingInt(a -> a[1])); ---> What if you sorted by the end time instead?

  // Step 2: Create a result list to store merged intervals
    List<int[]> result = new ArrayList<>();

    // Step 3: Start with the first interval as the 'current' one to compare with others
    int[] current = intervals[0];
    result.add(current);  // Add the first interval to result

    // Step 4: Loop through the rest of the intervals
    for (int[] interval : intervals) {
        // Check if the current interval overlaps with the next one
        if (current[1] >= interval[0]) {
            // Overlapping intervals -> merge them
            // Update the end of the current interval with the max end time
            current[1] = Math.max(current[1], interval[1]);

            // Example:
            // current = [1,3], next = [2,6] -> merged to [1,6]
        } else {
            // No overlap -> move to the next interval
            // Update 'current' to the new interval and add to result
            current = interval;
            result.add(current);
        }
    }

    // Convert result list to a 2D array
    return result.toArray(new int[result.size()][]);
}


============================================
  
 4. Detect Cycle in LinkedList (Pattern: Floyd’s Cycle Detection)

  public boolean hasCycle(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) return true;
    }
    return false;
}

-----------------------------------------
5. Kth Largest Element in Array (Pattern: Heap / QuickSelect)

  public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    for (int num : nums) {
        minHeap.offer(num);
        if (minHeap.size() > k) {
            minHeap.poll();
        }
    }
    return minHeap.peek();

  ------------------------------
  find k th smallest 


    public int findKthSmallest(int[] nums, int k) {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    
    for (int num : nums) {
        maxHeap.offer(num);
        if (maxHeap.size() > k) {
            maxHeap.poll();  // Remove the largest element if size exceeds k
        }
    }
    return maxHeap.peek();  // The root is the kth smallest element
}

  Operation	Time Complexity        	Space Complexity
k-th Largest Heap	O(n log k)	           O(k)
k-th Smallest Heap	O(n log k)          	O(k)

  -----------------------------------------------------
  6. LRU Cache (Pattern: LinkedHashMap / Custom Doubly Linked List)

  class LRUCache extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true); // accessOrder = true
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}




----------------------------
 7. Top K Frequent Elements (Pattern: HashMap + Heap)

  public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> freq = new HashMap<>();
    for (int num : nums) freq.put(num, freq.getOrDefault(num, 0) + 1);
    
    PriorityQueue<Map.Entry<Integer, Integer>> heap = 
        new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
    
    for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
        heap.offer(entry);
        if (heap.size() > k) heap.poll();
    }
    
    int[] res = new int[k];
    for (int i = k - 1; i >= 0; i--) {
        res[i] = heap.poll().getKey();
    }
    return res;
}

-------------------------------------------------

  8. Word Break (Pattern: DP + Trie)

  public boolean wordBreak(String s, List<String> wordDict) {
    Set<String> wordSet = new HashSet<>(wordDict);
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;

    for (int i = 1; i <= s.length(); i++) {
        for (int j = 0; j < i; j++) {
            if (dp[j] && wordSet.contains(s.substring(j, i))) {
                dp[i] = true;
                break;
            }
        }
    }
    return dp[s.length()];
}


--------------------------------------------------------
  9. Lowest Common Ancestor in BST

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (p.val < root.val && q.val < root.val)
        return lowestCommonAncestor(root.left, p, q);
    else if (p.val > root.val && q.val > root.val)
        return lowestCommonAncestor(root.right, p, q);
    else
        return root;
}


---------------------------------------
   10. Serialize and Deserialize Binary Tree

  public class Codec {
    public String serialize(TreeNode root) {
        if (root == null) return "null";
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }

    public TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Queue<String> nodes) {
        String val = nodes.poll();
        if (val.equals("null")) return null;
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = buildTree(nodes);
        node.right = buildTree(nodes);
        return node;
    }
}


---------------------
  11. Longest Palindromic Substring (Pattern: Expand Around Center)

public String longestPalindrome(String s) {
    if (s == null || s.length() < 1) return "";

    int start = 0, end = 0;

    for (int i = 0; i < s.length(); i++) {
        // len1: odd length palindrome (e.g., "aba" at i=1 in "babad")
        int len1 = expandFromCenter(s, i, i);

        // len2: even length palindrome (e.g., "bb" at i=1 in "cbbd")
        int len2 = expandFromCenter(s, i, i + 1);

        // We take both because palindrome can be odd or even
        int len = Math.max(len1, len2);

        if (len > end - start) {
            // update the indices if a longer palindrome is found
            start = i - (len - 1) / 2;
            end = i + len / 2;
        }
    }

    return s.substring(start, end + 1); // return longest palindrome substring
}

private int expandFromCenter(String s, int left, int right) {
    // Expand while the chars at left and right are equal
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
        left--;
        right++;
    }
    return right - left - 1; // final length of palindrome
}


Input: "babad"
→ i=0: "b", i=1: "bab", i=2: "aba"
✔ Longest = "bab" or "aba"

Input: "cbbd"
→ i=1: "bb" (even length!)
✔ Longest = "bb"

TC: O(n^2)
SC:O(1)

------------------------------------------------------------
   12. Clone Graph (Pattern: DFS + HashMap)

  class Node {
    public int val;
    public List<Node> neighbors;
    public Node(int val) {
        this.val = val;
        neighbors = new ArrayList<>();
    }
}

public Node cloneGraph(Node node) {
    if (node == null) return null;
    Map<Node, Node> map = new HashMap<>();
    return dfs(node, map);
}

private Node dfs(Node node, Map<Node, Node> map) {
    if (map.containsKey(node)) return map.get(node);

    Node clone = new Node(node.val);
    map.put(node, clone);

    for (Node neighbor : node.neighbors) {
        clone.neighbors.add(dfs(neighbor, map));
    }

    return clone;
}


Time: O(N + E), N = number of nodes, E = edges

Space: O(N)
----------------------------------------------------
  
 13. Course Schedule (Pattern: Topological Sort - Kahn's Algo)



  public boolean canFinish(int numCourses, int[][] prerequisites) {
    int[] inDegree = new int[numCourses];
    List<List<Integer>> graph = new ArrayList<>();

    for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());

    for (int[] pair : prerequisites) {
        graph.get(pair[1]).add(pair[0]);
        inDegree[pair[0]]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++)
        if (inDegree[i] == 0) queue.add(i);

    int count = 0;
    while (!queue.isEmpty()) {
        int course = queue.poll();
        count++;

        for (int neighbor : graph.get(course)) {
            inDegree[neighbor]--;
            if (inDegree[neighbor] == 0) queue.add(neighbor);
        }
    }

    return count == numCourses;
}



14. Trapping Rain Water (Pattern: Two Pointers)

  public int trap(int[] height) {
    int left = 0, right = height.length - 1;
    int leftMax = 0, rightMax = 0, result = 0;

    while (left < right) {
        if (height[left] < height[right]) {
            if (height[left] >= leftMax)
                leftMax = height[left];
            else
                result += leftMax - height[left];
            left++;
        } else {
            if (height[right] >= rightMax)
                rightMax = height[right];
            else
                result += rightMax - height[right];
            right--;
        }
    }

    return result;
}


Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6


  15. Subset Sum / All Subsets (Pattern: Backtracking)

  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(nums, 0, new ArrayList<>(), result);
    return result;
}

private void backtrack(int[] nums, int start, List<Integer> temp, List<List<Integer>> result) {
    result.add(new ArrayList<>(temp));
    for (int i = start; i < nums.length; i++) {
        temp.add(nums[i]);
        backtrack(nums, i + 1, temp, result);
        temp.remove(temp.size() - 1);
    }
}

Input: [1,2,3]
Output: [[], [1], [1,2], [1,2,3], [1,3], [2], [2,3], [3]]


  ----------------------------------------
  Coming Up Next (Let me know if you want):
Word Ladder (BFS + HashMap)

Sliding Window Maximum (Deque)

Median from Data Stream (Two Heaps)

Kth Smallest in BST (In-order Traversal)

Trie-based Word Dictionary

  
  
    
  16. Word Ladder (Pattern: BFS + HashSet)
🧠 Problem:
Given two words (beginWord and endWord), and a dictionary, return the length of the shortest transformation sequence where:

Only one letter can be changed at a time.

Each transformed word must exist in the word list.

✅ Pattern: Breadth-First Search
java
Copy
Edit
public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> dict = new HashSet<>(wordList);
    if (!dict.contains(endWord)) return 0;

    Queue<String> queue = new LinkedList<>();
    queue.offer(beginWord);
    int level = 1;

    while (!queue.isEmpty()) {
        int size = queue.size();
        while (size-- > 0) {
            String word = queue.poll();
            char[] chars = word.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                char old = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    String next = new String(chars);
                    if (next.equals(endWord)) return level + 1;
                    if (dict.contains(next)) {
                        queue.offer(next);
                        dict.remove(next); // prevent cycles
                    }
                }
                chars[i] = old;
            }
        }
        level++;
    }

    return 0;
}
📘 Example:
java
Copy
Edit
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5 ("hit"→"hot"→"dot"→"dog"→"cog")
⏱️ Complexity:
Time: O(N * M^2) where N = words, M = length of each word

Space: O(N)

🔁 17. Sliding Window Maximum (Pattern: Deque)
🧠 Problem:
Given an array and a window size k, return the max in each window.

✅ Pattern: Sliding Window + Monotonic Queue (Deque)
java
Copy
Edit
public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || k <= 0) return new int[0];
    int n = nums.length;
    int[] result = new int[n - k + 1];
    Deque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
        // Remove out of window
        if (!deque.isEmpty() && deque.peek() < i - k + 1) deque.poll();

        // Remove smaller values
        while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
            deque.pollLast();
        }

        deque.offer(i);

        if (i >= k - 1) {
            result[i - k + 1] = nums[deque.peek()];
        }
    }
    return result;
}
📘 Example:
java
Copy
Edit
Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
⏱️ Complexity:
Time: O(n)

Space: O(k)

🔁 18. Median from Data Stream (Pattern: Two Heaps)
🧠 Problem:
Design a class that supports adding numbers and finding the median.

✅ Pattern: Two Heaps (Max Heap + Min Heap)
java
Copy
Edit
class MedianFinder {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (maxHeap.size() < minHeap.size())
            maxHeap.offer(minHeap.poll());
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size())
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        else
            return maxHeap.peek();
    }
}
📘 Example:
java
Copy
Edit
addNum(1), addNum(2), findMedian() → 1.5
addNum(3), findMedian() → 2
⏱️ Complexity:
Time: O(log n)

Space: O(n)

🔁 19. Kth Smallest in BST (Pattern: Inorder Traversal)
🧠 Problem:
Find the Kth smallest element in a BST.

✅ Java Solution (Inorder Traversal):
java
Copy
Edit
public int kthSmallest(TreeNode root, int k) {
    Stack<TreeNode> stack = new Stack<>();

    while (true) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }

        root = stack.pop();
        if (--k == 0) return root.val;
        root = root.right;
    }
}
📘 Example:
java
Copy
Edit
Tree:     3
        /   \
       1     4
        \
         2

k = 1 → Output: 1
⏱️ Complexity:
Time: O(H + k)

Space: O(H), H = tree height

🔁 20. Implement Trie (Prefix Tree)
🧠 Problem:
Implement insert, search, and startsWith methods of a Trie.

✅ Java Trie Implementation:
java
Copy
Edit
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd = false;
}

class Trie {
    TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null)
                node.children[c - 'a'] = new TrieNode();
            node = node.children[c - 'a'];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null)
                return null;
            node = node.children[c - 'a'];
        }
        return node;
    }
}
📘 Example:
java
Copy
Edit
trie.insert("apple");
trie.search("apple");    // true
trie.startsWith("app");  // true
⏱️ Complexity:
Insert/Search: O(m)

Space: O(m * n), n = number of words, m = word length

🔜 Next Set Preview (21–25):
Would you like me to continue with:

✅ Top K Frequent Elements (Heap/Map)

✅ Merge K Sorted Lists (Heap)

✅ LRU Cache (LinkedHashMap/Custom)

✅ Detect Cycle in Graph (DFS)

✅ Serialize and Deserialize Binary Tree (BFS/DFS)

Let me know which direction you'd prefer:

More Graph/Heap/Greedy/DP

More System Design + DSA blend

Export to PDF/Markdown











  
