### Example Question: Coin Change Problem (Minimum Coins)

**Problem Statement:**

You are given an array of coin denominations `coins` (e.g., `[1, 2, 5]`) and a target `amount` (e.g., `11`). Write a function to compute the **minimum number of coins** needed to make up that `amount`. If that `amount` cannot be made up by any combination of the coins, return `-1`.

Assume you have an infinite supply of each coin type.

**Examples:**

1.  **Input:** `coins = [1, 2, 5]`, `amount = 11`
    **Output:** `3`
    **Explanation:** `11 = 5 + 5 + 1` (3 coins)

2.  **Input:** `coins = [2]`, `amount = 3`
    **Output:** `-1`
    **Explanation:** 3 cannot be made using only coins of denomination 2.

3.  **Input:** `coins = [1]`, `amount = 0`
    **Output:** `0`
    **Explanation:** Zero coins are needed to make an amount of zero.

---

### Why this problem can be solved with Dynamic Programming:

This problem exhibits the two key properties required for Dynamic Programming:

1.  **Optimal Substructure:**
    *   The optimal solution for making `amount` can be constructed from optimal solutions for making smaller amounts.
    *   If the minimum number of coins to make `amount` is `k`, and one of the coins used is `C`, then the remaining `k-1` coins must represent the minimum number of coins needed to make `amount - C`.
    *   In simpler terms: `minCoins(amount) = 1 + minCoins(amount - coin_i)` for some `coin_i`. We want to choose the `coin_i` that minimizes this expression.

2.  **Overlapping Subproblems:**
    *   When trying to compute `minCoins(amount)`, you might recursively call `minCoins(amount - C1)`, `minCoins(amount - C2)`, etc.
    *   These recursive calls will often lead to the same subproblems being calculated multiple times. For instance, `minCoins(10)` might need `minCoins(5)` (if using a 5-coin), and `minCoins(7)` might also need `minCoins(5)` (if using a 2-coin). Without memoization or tabulation, `minCoins(5)` would be recomputed.

---

### DP Approach (Tabulation / Bottom-Up):

Let's use an array, `dp`, where `dp[i]` will store the minimum number of coins needed to make an amount `i`.

1.  **Initialization:**
    *   Create a `dp` array of size `amount + 1`.
    *   `dp[0] = 0` (0 coins needed for amount 0).
    *   Initialize all other `dp[i]` for `i > 0` to `infinity` (or a very large number like `amount + 1` or `float('inf')`), indicating that these amounts are not yet reachable.

2.  **Iteration:**
    *   Iterate through each possible `amount` from `1` up to the `target amount` (`i` from `1` to `amount`).
    *   For each `i`, iterate through each `coin` denomination in `coins`.
    *   If the current `coin` denomination is less than or equal to `i` (i.e., `i - coin >= 0`):
        *   We can potentially use this `coin` to make `i`.
        *   The number of coins needed would be `1` (for the current `coin`) plus the minimum coins needed for the remaining amount `(i - coin)`.
        *   Update `dp[i] = min(dp[i], 1 + dp[i - coin])`. This means we're trying to find the minimum between the current best way to make `i` and making `i - coin` plus one more `coin`.

3.  **Result:**
    *   After filling the `dp` table, `dp[amount]` will contain the minimum number of coins.
    *   If `dp[amount]` is still `infinity` (or the initial large value), it means the amount cannot be made, so return `-1`. Otherwise, return `dp[amount]`.

---

### Walkthrough Example: `coins = [1, 2, 5]`, `amount = 11`

Initialize `dp` array of size `12` (for amounts 0 to 11) with `infinity` and `dp[0] = 0`.

`dp = [0, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf]`

---

**Processing `i = 1`:**
*   `coin = 1`: `dp[1] = min(inf, 1 + dp[1-1]) = min(inf, 1 + dp[0]) = min(inf, 1 + 0) = 1`
*   `coin = 2` (too large)
*   `coin = 5` (too large)
`dp = [0, 1, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf]`

**Processing `i = 2`:**
*   `coin = 1`: `dp[2] = min(inf, 1 + dp[2-1]) = min(inf, 1 + dp[1]) = min(inf, 1 + 1) = 2`
*   `coin = 2`: `dp[2] = min(2, 1 + dp[2-2]) = min(2, 1 + dp[0]) = min(2, 1 + 0) = 1`
*   `coin = 5` (too large)
`dp = [0, 1, 1, inf, inf, inf, inf, inf, inf, inf, inf, inf]`

**Processing `i = 3`:**
*   `coin = 1`: `dp[3] = min(inf, 1 + dp[2]) = min(inf, 1 + 1) = 2`
*   `coin = 2`: `dp[3] = min(2, 1 + dp[1]) = min(2, 1 + 1) = 2`
*   `coin = 5` (too large)
`dp = [0, 1, 1, 2, inf, inf, inf, inf, inf, inf, inf, inf]`

**... (and so on) ...**

**Processing `i = 5`:**
*   `coin = 1`: `dp[5] = min(inf, 1 + dp[4])` (let's assume dp[4] was 2 from 2+2 or 1+1+1+1) = `1+2=3`
*   `coin = 2`: `dp[5] = min(3, 1 + dp[3]) = min(3, 1 + 2) = 3`
*   `coin = 5`: `dp[5] = min(3, 1 + dp[0]) = min(3, 1 + 0) = 1`
`dp = [0, 1, 1, 2, 2, 1, 2, 2, 3, 3, 2, inf]` (Values for 0-4 are derived similarly)

**... (eventually we reach `i = 11`) ...**

**Processing `i = 11`:**
*   Current `dp[11]` is `inf`.
*   `coin = 1`: `dp[11] = min(inf, 1 + dp[11-1]) = min(inf, 1 + dp[10])`
    *   (Assuming `dp[10]` was calculated as `2` using `5+5`)
    *   `dp[11] = min(inf, 1 + 2) = 3`
*   `coin = 2`: `dp[11] = min(3, 1 + dp[11-2]) = min(3, 1 + dp[9])`
    *   (Assuming `dp[9]` was calculated as `3` using `5+2+2`)
    *   `dp[11] = min(3, 1 + 3) = 3`
*   `coin = 5`: `dp[11] = min(3, 1 + dp[11-5]) = min(3, 1 + dp[6])`
    *   (Assuming `dp[6]` was calculated as `2` using `5+1`)
    *   `dp[11] = min(3, 1 + 2) = 3`

Final `dp[11]` is `3`.

---