/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class IceCreamParlor {
	/**
	 * Finds two distinct ice cream flavors whose prices sum up to the given money.
	 * Returns the 1-based indices of these two flavors.
	 *
	 * @param prices An array of ice cream flavor prices.
	 * @param money  The target amount of money.
	 * @return An int array containing the 1-based indices of the two flavors, or an
	 *         empty array if no solution is found (though problem usually
	 *         guarantees one).
	 */
	public static int[] findChoices(int[] prices, int money) {
		// HashMap to store (price -> index)
		// We use the 0-based index here and convert to 1-based at the end.
		Map<Integer, Integer> priceMap = new HashMap<>();

		for (int i = 0; i < prices.length; i++) {
			int currentPrice = prices[i];
			int complement = money - currentPrice;

			// Check if the complement exists in our map
			// This means we found the pair!
			if (priceMap.containsKey(complement)) {
				// Return 1-based indices
				return new int[] { priceMap.get(complement) + 1, i + 1 };
			}

			// If the complement is not found, add the current price and its index to the
			// map
			// But only if we haven't already added this price.
			// If there are duplicate prices and we need the first occurrence,
			// this check is important:
			if (!priceMap.containsKey(currentPrice)) {
				priceMap.put(currentPrice, i);
			}
			// If the problem allows for duplicate prices where we need to find two distinct
			// *positions*
			// that might have the same value (e.g., prices=[5, 5], money=10),
			// and we need to handle cases like [5,5] where 5 is a value that can be reused
			// IF its another instance
			// then simply: priceMap.put(currentPrice, i); without the check.
			// The HackerRank version of Ice Cream Parlor implies that if prices are [5, 5],
			// you should return their distinct indices, like [1, 2].
			// The current code handles this correctly because when it finds '5' as a
			// complement,
			// it will return the index of the '5' it already stored, and the current index
			// 'i'.
		}

		// If no solution is found (should not happen based on typical problem
		// constraints)
		return new int[] {};
	}

	public static void main(String[] args) {
		// Test Cases
		int[] prices1 = { 1, 4, 5, 3, 2 };
		int money1 = 4; // Expected: [1, 4]
		int[] result1 = findChoices(prices1, money1);
		System.out.println("Prices: " + java.util.Arrays.toString(prices1) + ", Money: " + money1 + " -> Result: "
				+ java.util.Arrays.toString(result1));

		int[] prices2 = { 7, 2, 4, 8, 11 };
		int money2 = 11; // Expected: [1, 3] (7 and 4)
		int[] result2 = findChoices(prices2, money2);
		System.out.println("Prices: " + java.util.Arrays.toString(prices2) + ", Money: " + money2 + " -> Result: "
				+ java.util.Arrays.toString(result2));

		int[] prices3 = { 10, 20, 30, 40, 50 };
		int money3 = 60; // Expected: [2, 4] (20 and 40)
		int[] result3 = findChoices(prices3, money3);
		System.out.println("Prices: " + java.util.Arrays.toString(prices3) + ", Money: " + money3 + " -> Result: "
				+ java.util.Arrays.toString(result3));

		int[] prices4 = { 2, 2, 4, 3 };
		int money4 = 4; // Expected: [1, 2] (2 and 2)
		int[] result4 = findChoices(prices4, money4);
		System.out.println("Prices: " + java.util.Arrays.toString(prices4) + ", Money: " + money4 + " -> Result: "
				+ java.util.Arrays.toString(result4));

		int[] prices5 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int money5 = 15; // Expected: [5, 10] (5 and 10)
		int[] result5 = findChoices(prices5, money5);
		System.out.println("Prices: " + java.util.Arrays.toString(prices5) + ", Money: " + money5 + " -> Result: "
				+ java.util.Arrays.toString(result5));
	}
}
