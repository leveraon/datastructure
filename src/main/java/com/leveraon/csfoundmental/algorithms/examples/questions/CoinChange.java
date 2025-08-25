package com.leveraon.csfoundmental.algorithms.examples.questions;

import java.util.Arrays;

public class CoinChange {
	/**
	 * Computes the minimum number of coins needed to make up a given amount.
	 *
	 * This method uses Dynamic Programming (tabulation/bottom-up approach). dp[i]
	 * represents the minimum number of coins required to make an amount 'i'.
	 *
	 * @param coins  An array of coin denominations. Assume an infinite supply of
	 *               each coin type.
	 * @param amount The target amount to make change for.
	 * @return The minimum number of coins needed, or -1 if the amount cannot be
	 *         made.
	 */
	public int coinChange(int[] coins, int amount) {
		// Create a dp array where dp[i] will store the minimum number of coins
		// needed to make an amount of 'i'.
		// The size is amount + 1 because we need to store results for amounts from 0 to
		// 'amount'.
		int[] dp = new int[amount + 1];

		// Initialize the dp array.
		// We use 'amount + 1' as a sentinel value to represent "infinity" or
		// "unreachable". This value is chosen because the maximum number of coins
		// needed to make any amount 'i' cannot exceed 'i' itself (e.g., using only
		// 1-unit coins).
		// So, 'amount + 1' is guaranteed to be larger than any valid coin count for
		// 'amount',
		// and importantly, avoids potential integer overflow issues that
		// `Integer.MAX_VALUE + 1`
		// might cause, turning into a negative number.
		Arrays.fill(dp, amount + 1);

		// Base case: 0 coins are needed to make an amount of 0.
		dp[0] = 0;

		// Iterate through each possible amount from 1 up to the target 'amount'.
		// This is our outer loop, building up solutions for larger amounts
		// from solutions of smaller amounts.
		for (int i = 1; i <= amount; i++) {
			// For each amount 'i', iterate through each available coin denomination.
			// We are trying to find the best previous state to transition from
			// by using one of the available coins.
			for (int coin : coins) {
				// Check if the current coin can be used to form amount 'i'.
				// This means 'i - coin' must be a valid non-negative amount.
				if (i - coin >= 0) {
					// If we use the current 'coin' to make amount 'i',
					// then the number of coins needed would be 1 (for the current coin)
					// plus the minimum number of coins needed for the remaining amount (i - coin).
					//
					// We update dp[i] with the minimum of its current value (which could be
					// from a previous coin choice or the initial 'infinity') and this new
					// possibility.
					//
					// The `1 + dp[i - coin]` part implicitly handles the "unreachable" case:
					// if `dp[i - coin]` is `amount + 1` (infinity), then `1 + (amount + 1)`
					// will be `amount + 2`, which is still greater than `amount + 1`,
					// so `Math.min` will correctly prioritize any reachable path or keep it
					// at `amount + 1` if no path is found yet.
					dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
				}
			}
		}

		// After filling the dp table, dp[amount] will contain the minimum number of
		// coins.
		// If dp[amount] is still 'amount + 1' (our initial "infinity" value),
		// it means the target amount cannot be made by any combination of the given
		// coins.
		return dp[amount] > amount ? -1 : dp[amount];
	}

	public static void main(String[] args) {
		CoinChange solver = new CoinChange();

		// Test cases
		int[] coins1 = { 1, 2, 5 };
		int amount1 = 11;
		System.out.println("Coins: " + Arrays.toString(coins1) + ", Amount: " + amount1 + " -> Minimum coins: "
				+ solver.coinChange(coins1, amount1)); // Expected: 3 (5+5+1)

		int[] coins2 = { 2 };
		int amount2 = 3;
		System.out.println("Coins: " + Arrays.toString(coins2) + ", Amount: " + amount2 + " -> Minimum coins: "
				+ solver.coinChange(coins2, amount2)); // Expected: -1

		int[] coins3 = { 1 };
		int amount3 = 0;
		System.out.println("Coins: " + Arrays.toString(coins3) + ", Amount: " + amount3 + " -> Minimum coins: "
				+ solver.coinChange(coins3, amount3)); // Expected: 0

		int[] coins4 = { 1 };
		int amount4 = 1;
		System.out.println("Coins: " + Arrays.toString(coins4) + ", Amount: " + amount4 + " -> Minimum coins: "
				+ solver.coinChange(coins4, amount4)); // Expected: 1

		int[] coins5 = { 186, 419, 83, 408 };
		int amount5 = 6249;
		System.out.println("Coins: " + Arrays.toString(coins5) + ", Amount: " + amount5 + " -> Minimum coins: "
				+ solver.coinChange(coins5, amount5)); // Expected: 20
	}
}
