// In this approach, generating all possible arithmetic slices of length 3 or more and checking the difference, if same then increasing
// the count else break.
// Time Complexity : O(n^2)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
// Bruteforce
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        // Base case
        if (nums == null || nums.length < 3) {
            return 0;
        }
        // Declare count
        int cnt = 0;
        int n = nums.length;
        // Outer loop from 0 to n-2
        for (int i = 0; i < n - 2; i++) {
            // Calc the difference
            int diff = nums[i + 1] - nums[i];
            // Inner loop from i+1 to n-1
            for (int j = i + 1; j < n - 1; j++) {
                // Check if difference of next is same as previous two
                if (nums[j + 1] - nums[j] == diff) {
                    // Increment count
                    cnt++;
                } else {
                    // Else break, start from next i
                    break;
                }
            }
        }
        // return count
        return cnt;
    }
}

// In this dp approach, store the number of arithmetic slices upto that value
// and moving forward use that to compute more.
// Iterate from start - dp array
// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        // Base case
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int cnt = 0;
        int n = nums.length;
        // Dp array of length n
        int[] dp = new int[n];
        // Start iterating from 3rd element because we need size 3 or more
        for (int i = 2; i < n; i++) {
            // So check if a->b->c... than c-b == b-a ??
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                // if difference is same, than for the current element value in dp arrar will be
                // 1+(dp value until previous element)
                dp[i] = 1 + dp[i - 1];
            }
            // And add the dp[i] to the count
            cnt = cnt + dp[i];
        }
        // return count
        return cnt;
    }
}

// Same dp approach but starting from end i.e. third last element
// Iterate from end - dp array
// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int cnt = 0;
        int n = nums.length;
        int[] dp = new int[n];
        // So loop from n-3 till 0
        for (int i = n - 3; i >= 0; i--) {
            // If ...x->y->z than start from x and compare, y-x == z-y ??
            if (nums[i + 1] - nums[i] == nums[i + 2] - nums[i + 1]) {
                // If equal than 1 + dp value of next element
                dp[i] = 1 + dp[i + 1];
            }
            cnt = cnt + dp[i];
        }
        return cnt;
    }
}

// In our dp approach at any point we are comparing 3 element values either
// backwards or in front. So why to store in dp array, we
// just need few variables like prev that will hold the value of number of
// arithmetic slices until previous element. So for the current
// one if the difference is same, than add 1 to the prev and add that prev value
// in cnt. If diff is not same than reset previous to 0.
// Time Complexity : O(n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
// Constant space
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        // Base case
        if (nums == null || nums.length < 3) {
            return 0;
        }
        // Declare the count and previous
        int cnt = 0;
        int n = nums.length;
        int prev = 0;
        // Loop from third element till the last
        for (int i = 2; i < n; i++) {
            // Check if the difference is same
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                // Add 1 to the previous
                prev = prev + 1;
                // And add this new previous value to the cnt
                cnt = cnt + prev;
            } else {
                // Else reset the previous to 0
                prev = 0;
            }

        }
        // Return count
        return cnt;
    }
}