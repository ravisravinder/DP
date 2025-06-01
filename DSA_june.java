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
  Top K Frequent Elements (Pattern: HashMap + Heap)

  
  
    
  











  
