// In this problem, starting to iterate from second last row and looking at next row, so taking the min of 2 elements from next row
// and adding it to the current element's value. At last the first cell will hold our minimum path sum.

// Time Complexity : O(m*n) where m is the no of rows and n being the average length of each row, in worst case O(n^2)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // Base case
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int n = triangle.size();
        // Start from the second last row
        for (int i = n - 2; i >= 0; i--) {
            // For each value in that row
            for (int j = 0; j < triangle.get(i).size(); j++) {
                // Add to it the min of the 2 elements in next row i.e [i+1][j] and [i+1][j+1]
                triangle.get(i).set(j,
                        triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
            }
        }
        // The first cell i.e. (0,0) will have the answer
        return triangle.get(0).get(0);
    }
}

// Same approach but just starting from second row and in each iteration looking
// at the previous row. Now for this approach in each row for the first value we
// only have access to the element present at same index in row above it and for
// the last value we only have access to the element present at the index one
// less in previous row. Our ans will be the min value from last row.

// Time Complexity : O(m*n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // Base case
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int n = triangle.size();
        // Loop from second row
        for (int i = 1; i < n; i++) {
            // For each value in that row, look in the previous row
            for (int j = 0; j < triangle.get(i).size(); j++) {
                // If it is first value, we only have i-1,j
                if (j == 0) {
                    triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i - 1).get(j));
                }
                // If last value, we only have i-1,j-1
                else if (j == triangle.get(i).size() - 1) {
                    triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i - 1).get(j - 1));
                }
                // Else for all middle values, we have both j and j-1 in previous row, so we are
                // taking minimum of two and adding to current
                else {
                    triangle.get(i).set(j, triangle.get(i).get(j)
                            + Math.min(triangle.get(i - 1).get(j), triangle.get(i - 1).get(j - 1)));
                }
            }
        }
        // Take min variable for getting the min
        int min = Integer.MAX_VALUE;
        // Iterate over last row
        for (int j = 0; j < triangle.get(n - 1).size(); j++) {
            // Store the min in min
            min = Math.min(min, triangle.get(n - 1).get(j));
        }
        // Return minimum
        return min;
    }
}