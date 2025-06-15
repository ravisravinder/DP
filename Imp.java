class Solution {
    public String largestNumber(int[] nums) {

        // Step 1: Convert each integer to a string

        String[] array = Arrays.stream(nums).mapToObj(String::valueOf).toArray(String[]::new);

        // Step 2: Sort the array using a custom comparator
        // If "b+a" is greater than "a+b", then b should come before a
        // Example: "9" + "34" = "934" vs "34" + "9" = "349" → "9" comes before "34"
        /*
         EXAMPLES:
        
         a = "3", b = "30"
         (b + a) = "303"
         (a + b) = "330"
         Since "330" > "303", we want "3" before "30"
        
         a = "9", b = "34"
         (b + a) = "349"
         (a + b) = "934"
         Since "934" > "349", we want "9" before "34"
        
         a = "5", b = "50"
         (b + a) = "505"
         (a + b) = "550"
         Since "550" > "505", "5" comes before "50"
        
         So we always compare (b + a) vs (a + b)
         If (b + a) > (a + b), that means b should come before a
         */
        Arrays.sort(array, (a, b) -> (b + a).compareTo(a + b));

        // Step 3: If the largest value is "0", then all are zeros → return "0"
        if (array[0].equals("0")) {
            return "0";
        }
        System.out.println(Arrays.toString(array));

        // Step 4: Concatenate the sorted strings
        StringBuilder largest = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            largest.append(array[i]);
        }

        // Step 5: Return the final result as a string
        return largest.toString();
    }
}
