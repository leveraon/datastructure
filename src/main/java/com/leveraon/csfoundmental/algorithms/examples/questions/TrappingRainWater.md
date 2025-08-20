### 5. **Trapping Rain Water**

*   **Problem:** Given `n` non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
*   **DSA Focus:** Arrays, Two Pointers, Stacks.
*   **Senior-Level Discussion Points:**
    *   Brute-force (for each bar, find left_max and right_max) - O(N^2) time.
    *   Two-pass approach: Precompute `left_max` and `right_max` arrays. Then iterate once to calculate water. O(N) time, O(N) space.
    *   **Optimal:** Two-pointer approach. Maintain `left_max` and `right_max` dynamically with two pointers moving inwards. This is the most efficient and elegant solution. O(N) time, O(1) space.
    *   Stack-based approach: Another valid O(N) time solution, often less intuitive but good for demonstrating knowledge of stacks for monotonic properties.
    *   Visualizing the problem and how water is trapped (min of left/right max minus current height).
    *   Edge cases: empty array, single bar, monotonically increasing/decreasing bars.

---
### Solution: Trapping Rain Water

**Problem:** Given `n` non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

**Approach:**
This problem has a few approaches, ranging from brute-force to optimal. The optimal solution uses a **Two-Pointer** technique, achieving O(N) time and O(1) space.

1.  **Understand the Logic:** For a bar at index `i`, the amount of water it can trap is determined by the minimum of the maximum height to its left and the maximum height to its right, minus its own height. `water_at_i = min(max_left_height, max_right_height) - height[i]`. If `water_at_i` is negative, it means no water can be trapped above it (it's a peak or higher than its surroundings), so we take `max(0, ...)` to ensure non-negative water.

2.  **Two-Pointer Strategy (Optimal):**
    This approach avoids pre-calculating `max_left` and `max_right` arrays, thus reducing space to O(1).
    *   Initialize `left` pointer at index `0`, `right` pointer at `n-1`.
    *   Initialize `left_max = 0`, `right_max = 0`, `total_water = 0`.
    *   Iterate while `left < right`:
        *   If `height[left] < height[right]`:
            *   This means the amount of water trapped at `left` (and potentially points between `left` and `right`) is limited by `height[left]`. We can confidently process the `left` side.
            *   If `height[left] >= left_max`: Update `left_max = height[left]`. (This bar itself forms a new "wall".)
            *   Else (`height[left] < left_max`): Water can be trapped. `total_water += left_max - height[left]`.
            *   Move `left++`.
        *   Else (`height[right] <= height[left]`):
            *   This means the amount of water trapped at `right` (and potentially points between `left` and `right`) is limited by `height[right]`. We can confidently process the `right` side.
            *   If `height[right] >= right_max`: Update `right_max = height[right]`.
            *   Else (`height[right] < right_max`): Water can be trapped. `total_water += right_max - height[right]`.
            *   Move `right--`.
    *   Why this works: When `height[left] < height[right]`, we know that the water level at `left` is determined *only* by `left_max` because there's definitely a wall `height[right]` (or higher) on the right side. The actual `right_max` doesn't matter for the water level at `left` because `height[right]` is already greater than `height[left]`. The same logic applies when `height[right] <= height[left]`.

**Time Complexity:** O(N), as each pointer traverses the array at most once.
**Space Complexity:** O(1), as only a few variables are used.

**Java Code:**

```java
import java.util.Arrays;

class Solution {

    /**
     * Computes the total amount of water trapped after raining, given an elevation map.
     * Uses the Two-Pointer approach for O(N) time and O(1) space complexity.
     *
     * @param height An array of non-negative integers representing the elevation map.
     * @return The total amount of trapped water.
     */
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0; // Not enough bars to trap water
        }

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int totalWater = 0;

        while (left < right) {
            // Process the side with the smaller current height, as it is the limiting factor.
            if (height[left] < height[right]) {
                // The water level at 'left' is determined by 'leftMax'.
                // Any water trapped here would be bounded by 'leftMax' on the left
                // and at least 'height[right]' (which is >= height[left]) on the right.
                if (height[left] >= leftMax) {
                    leftMax = height[left]; // Update the left maximum wall
                } else {
                    totalWater += leftMax - height[left]; // Trap water
                }
                left++; // Move left pointer inwards
            } else {
                // The water level at 'right' is determined by 'rightMax'.
                // Any water trapped here would be bounded by 'rightMax' on the right
                // and at least 'height[left]' (which is >= height[right]) on the left.
                if (height[right] >= rightMax) {
                    rightMax = height[right]; // Update the right maximum wall
                } else {
                    totalWater += rightMax - height[right]; // Trap water
                }
                right--; // Move right pointer inwards
            }
        }

        return totalWater;
    }

    // --- Example Usage ---
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Test Case 1: Standard example
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        // Expected: 6 (1+1+2+1+1)
        System.out.println("Height: " + Arrays.toString(height1));
        System.out.println("Trapped Water: " + sol.trap(height1));

        // Test Case 2: Monotonically increasing (no water)
        int[] height2 = {4, 2, 0, 3, 2, 5};
        // Expected: 9 (2+4+1+2)
        System.out.println("\nHeight: " + Arrays.toString(height2));
        System.out.println("Trapped Water: " + sol.trap(height2));

        // Test Case 3: Monotonically increasing (no water)
        int[] height3 = {1, 2, 3, 4, 5};
        // Expected: 0
        System.out.println("\nHeight: " + Arrays.toString(height3));
        System.out.println("Trapped Water: " + sol.trap(height3));

        // Test Case 4: Monotonically decreasing (no water)
        int[] height4 = {5, 4, 3, 2, 1};
        // Expected: 0
        System.out.println("\nHeight: " + Arrays.toString(height4));
        System.out.println("Trapped Water: " + sol.trap(height4));

        // Test Case 5: Empty array
        int[] height5 = {};
        // Expected: 0
        System.out.println("\nHeight: " + Arrays.toString(height5));
        System.out.println("Trapped Water: " + sol.trap(height5));

        // Test Case 6: Short array (less than 3 bars)
        int[] height6 = {1, 2};
        // Expected: 0
        System.out.println("\nHeight: " + Arrays.toString(height6));
        System.out.println("Trapped Water: " + sol.trap(height6));
    }
}
```

**Senior-Level Considerations:**

*   **Evolution of Solutions:** Discuss the various approaches:
    *   **Brute-Force (O(N^2) time, O(1) space):** For each bar, iterate left and right to find `max_left` and `max_right`.
    *   **Dynamic Programming / Pre-computation (O(N) time, O(N) space):** Create two arrays, `leftMax[]` and `rightMax[]`, in two passes. Then, in a third pass, calculate water. This is a good intermediate step if the two-pointer solution isn't immediately obvious.
    *   **Two-Pointer (Optimal - O(N) time, O(1) space):** The provided solution. Explain why this works by guaranteeing that at each step, the boundary chosen (`height[left]` or `height[right]`) is indeed the limiting factor for water on that side.
    *   **Stack-based (O(N) time, O(N) space):** Another valid approach using a monotonic stack to find the left and right boundaries for each potential "valley". This is a good alternative to mention if the two-pointer isn't hit immediately.
*   **Intuition:** Clearly explain *why* the two-pointer approach works. The key insight is that if `height[left] < height[right]`, then the water level at `left` can only be limited by `leftMax`, because `height[right]` (or anything further right) is guaranteed to be at least as tall as `height[left]`.
*   **Edge Cases:** Discuss handling empty arrays, arrays with fewer than 3 bars (no water can be trapped), and monotonically increasing/decreasing bars.

---