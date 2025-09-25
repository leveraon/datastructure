The problem asks us to find the maximum coins we can collect by bursting all balloons. When a balloon `i` is burst, we get `nums[left] * nums[i] * nums[right]` coins, where `left` and `right` are its adjacent balloons. After `i` is burst, `left` and `right` become adjacent.

**Understanding the Challenge**

A direct greedy approach (e.g., always bursting the smallest or largest balloon) won't work because the choice of which balloon to burst affects the neighbors of other balloons, changing their future coin values. This hints at a dynamic programming approach.

The main difficulty with typical interval DP (`dp[i][j]` = max coins for `nums[i...j]`) is that when we burst a balloon `k` within `[i, j]`, its neighbors `left` and `right` might be *outside* the `[i, j]` range. This makes combining subproblems complex.

**The Key Insight: Bursting the Last Balloon**

Instead of thinking about which balloon to burst *first* in an interval, consider which balloon to burst *last*. This seemingly counter-intuitive reversal simplifies the problem significantly.

Let's define `dp[i][j]` as the maximum coins obtained by bursting all balloons *strictly between* index `i` and index `j`. To handle edge cases where balloons are at the ends of the original array, we pad the `nums` array with `1`s at the beginning and end. These `1`s represent imaginary balloons that cannot be burst but act as boundaries.

Let the modified array be `paddedNums`. If `nums` has `n` balloons, `paddedNums` will have `n + 2` elements:
`paddedNums = [1, nums[0], nums[1], ..., nums[n-1], 1]`

Now, consider `dp[i][j]`. We want to burst all balloons in the open interval `(i, j)`. This means `paddedNums[i+1], ..., paddedNums[j-1]`.
If we choose `paddedNums[k]` (where `i < k < j`) to be the *last* balloon burst in this interval, then at the moment `paddedNums[k]` is burst, its immediate neighbors *must* be `paddedNums[i]` and `paddedNums[j]`. This is because all other balloons in `(i, j)` have already been burst.

The coins obtained from bursting `paddedNums[k]` in this scenario will be `paddedNums[i] * paddedNums[k] * paddedNums[j]`.
After `paddedNums[k]` is burst, the problem splits into two independent subproblems:
1.  Bursting all balloons in the interval `(i, k)`: This contributes `dp[i][k]` coins.
2.  Bursting all balloons in the interval `(k, j)`: This contributes `dp[k][j]` coins.

**The Recurrence Relation:**

`dp[i][j] = max (for i < k < j) { dp[i][k] + dp[k][j] + paddedNums[i] * paddedNums[k] * paddedNums[j] }`

**Base Cases:**
If there are no balloons strictly between `i` and `j` (i.e., `j = i + 1`), then `dp[i][j] = 0`. The `dp` table is usually initialized to 0, which conveniently handles these base cases.

**Dynamic Programming Approach:**

1.  **Padding:** Create `paddedNums` array:
    *   `paddedNums[0] = 1`
    *   `paddedNums[n+1] = 1`
    *   For `i` from `0` to `n-1`, `paddedNums[i+1] = nums[i]`.
    *   The total length of `paddedNums` is `N = n + 2`.

2.  **DP Table:** Create a 2D array `dp[N][N]` initialized to 0.

3.  **Iteration Order:** We need to compute `dp[i][k]` and `dp[k][j]` before `dp[i][j]`. This means we should iterate by the length of the interval.
    *   `len` (length of the open interval `(i, j)`) will range from `2` (meaning one balloon `paddedNums[i+1]`) up to `N-1` (meaning all original `n` balloons between `paddedNums[0]` and `paddedNums[n+1]`).
    *   `i` (start index) will iterate from `0` up to `N - len - 1`.
    *   `j` (end index) will be `i + len`.
    *   `k` (the last balloon burst) will iterate from `i + 1` up to `j - 1`.

4.  **Result:** The maximum coins will be stored in `dp[0][n+1]`, representing the bursting of all original balloons between the two virtual `1`s.

**Example: `nums = [3, 1, 5, 8]`**

1.  `n = 4`.
2.  `paddedNums = [1, 3, 1, 5, 8, 1]` (length `N = 6`).
3.  `dp` table `6x6`, initialized to 0.

**Iteration:**

*   `len = 2`:
    *   `i=0, j=2`: `dp[0][2]` (burst `paddedNums[1]=3`)
        *   `k=1`: `dp[0][1]+dp[1][2] + 1*3*1 = 0+0+3 = 3`. `dp[0][2]=3`.
    *   `i=1, j=3`: `dp[1][3]` (burst `paddedNums[2]=1`)
        *   `k=2`: `dp[1][2]+dp[2][3] + 3*1*5 = 0+0+15 = 15`. `dp[1][3]=15`.
    *   ...and so on for `(2,4)` and `(3,5)`.

*   `len = 3`:
    *   `i=0, j=3`: `dp[0][3]` (burst `paddedNums[1]=3, paddedNums[2]=1`)
        *   `k=1` (last burst `3`): `dp[0][1]+dp[1][3] + 1*3*5 = 0+15+15 = 30`.
        *   `k=2` (last burst `1`): `dp[0][2]+dp[2][3] + 1*1*5 = 3+0+5 = 8`.
        *   `dp[0][3] = max(30, 8) = 30`.
    *   ...and so on for `(1,4)` and `(2,5)`.

*   ...this continues until `len = N-1 = 5`.

*   `len = 5`:
    *   `i=0, j=5`: `dp[0][5]` (burst `3, 1, 5, 8`)
        *   `k=1` (last burst `3`): `dp[0][1]+dp[1][5] + 1*3*1 = 0 + 159 + 3 = 162`.
        *   `k=2` (last burst `1`): `dp[0][2]+dp[2][5] + 1*1*1 = 3 + 48 + 1 = 52`.
        *   `k=3` (last burst `5`): `dp[0][3]+dp[3][5] + 1*5*1 = 30 + 40 + 5 = 75`.
        *   `k=4` (last burst `8`): `dp[0][4]+dp[4][5] + 1*8*1 = 159 + 0 + 8 = 167`.
        *   `dp[0][5] = max(162, 52, 75, 167) = 167`.

The final answer will be `dp[0][n+1]`.

**Complexity:**

*   **Time Complexity:** `O(N^3)` where `N = n + 2`. There are `O(N^2)` states, and each state calculation involves an `O(N)` loop.
*   **Space Complexity:** `O(N^2)` for the `dp` table.

Given `n` up to 500, `N` is about 502. `502^3` is approximately `1.2 * 10^8`, which is generally acceptable for a few seconds time limit.

```java
import java.util.Arrays; // Not strictly necessary but often useful

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;

        // Create a new array with padded 1s at both ends.
        // This simplifies handling boundary conditions: if we burst a balloon at the original
        // array's edge, its virtual neighbor (value 1) is used for multiplication.
        // paddedNums will have length n + 2.
        int[] paddedNums = new int[n + 2];
        paddedNums[0] = 1; // Left virtual balloon
        paddedNums[n + 1] = 1; // Right virtual balloon
        for (int i = 0; i < n; i++) {
            paddedNums[i + 1] = nums[i]; // Copy original balloons into the padded array
        }

        // dp[i][j] will store the maximum coins obtained by bursting all balloons
        // strictly between index i and index j (exclusive of i and j) in the paddedNums array.
        // The indices i and j refer to the paddedNums array.
        // Java initializes int arrays to 0 by default, which is suitable here.
        int[][] dp = new int[n + 2][n + 2];

        // Iterate over the length of the interval.
        // 'len' represents the number of elements *between* i and j.
        // A length of 2 means (i, i+2), implying one balloon (paddedNums[i+1]).
        // A length of n+1 means (0, n+1), implying all 'n' original balloons.
        for (int len = 2; len <= n + 1; len++) {
            // Iterate over the starting index 'i' of the interval (i, j).
            // 'i' can go from 0 up to (total_padded_length - 1) - len.
            // For example, if paddedNums.length is N, max j is N-1.
            // j = i + len. So, i + len <= N-1 => i <= N - 1 - len.
            // Here, N = n + 2, so i <= (n + 2) - 1 - len = n + 1 - len.
            for (int i = 0; i <= n + 1 - len; i++) {
                int j = i + len; // Calculate the end index 'j' for the current interval (i, j)

                // Iterate over all possible balloons 'k' that could be the *last* balloon to burst
                // within the open interval (i, j).
                // 'k' must be strictly between 'i' and 'j'.
                for (int k = i + 1; k < j; k++) {
                    // If paddedNums[k] is the last balloon to be burst in (i, j):
                    // 1. We earn coins from bursting paddedNums[k]: paddedNums[i] * paddedNums[k] * paddedNums[j].
                    //    At this moment, paddedNums[i] and paddedNums[j] are the immediate neighbors of paddedNums[k].
                    // 2. We add the maximum coins from bursting balloons in the sub-interval (i, k): dp[i][k].
                    // 3. We add the maximum coins from bursting balloons in the sub-interval (k, j): dp[k][j].
                    // These two subproblems (i, k) and (k, j) are independent because k is burst last.
                    // All balloons in (i, k) are burst (leaving i and k adjacent).
                    // All balloons in (k, j) are burst (leaving k and j adjacent).
                    // Then, k is burst using i and j as its final neighbors.
                    dp[i][j] = Math.max(dp[i][j], 
                                        dp[i][k] + dp[k][j] + 
                                        paddedNums[i] * paddedNums[k] * paddedNums[j]);
                }
            }
        }

        // The final result is the maximum coins for bursting all balloons between index 0 and n+1
        // in the paddedNums array, which corresponds to bursting all the original 'n' balloons.
        return dp[0][n + 1];
    }
}
```