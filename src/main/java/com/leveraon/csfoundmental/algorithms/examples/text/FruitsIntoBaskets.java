/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples.text;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Given an array of characters where each character represents a fruit tree,
 * you are given two baskets and your goal is to put maximum number of fruits in
 * each basket. The only restriction is that each basket can have only one type
 * of fruit. <br/>
 * <br/>
 * You can start with any tree, but once you have started you can’t skip a
 * tree.<br/>
 * You will pick one fruit from each tree until you cannot, i.e., you will stop
 * when you have to pick from a third fruit type.<br/>
 * 
 * Write a function to return the maximum number of fruits in both the baskets.
 * </p>
 * 
 */
@Slf4j
public class FruitsIntoBaskets {
	public Map<Character, Integer> fruitsIntoBaskets(char[] arr) {
		int windowStart = 0;
		int maxFruits = 0;
		Map<Character, Integer> mp = new HashMap<>();

		for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
			mp.put(arr[windowEnd], mp.getOrDefault(arr[windowEnd], 0) + 1);

			while (mp.size() > 2) {
				mp.put(arr[windowStart], mp.get(arr[windowStart]) - 1);
				if (mp.get(arr[windowStart]) == 0) {
					mp.remove(arr[windowStart]);
				}
				windowStart++;
			}
			maxFruits = Math.max(maxFruits, windowEnd - windowStart + 1);
		}
		log.info("Max fruits in baskets {}", maxFruits);
		return mp;
	}

	public static void main(String[] args) {
		FruitsIntoBaskets fib = new FruitsIntoBaskets();
		char[] test = new char[] { 'A', 'B', 'D', 'B', 'E', 'C', 'A', 'B', 'C', 'F' };
		Map<Character, Integer> max = fib.fruitsIntoBaskets(test);

		log.info("Max fruits in baskets {}", max);

	}

}
