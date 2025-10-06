/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples.text;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * <h2>Given a string, find the length of the longest substring in it with no
 * more than K distinct characters.</h2> <br/>
 * Input: String="araaci", K=2 <br/>
 * Output: 4 <br/>
 * Explanation: The longest substring with no more than '2' distinct characters
 * is "araa".
 * 
 */
@Slf4j
public class LongestKthDistinctSubString {

	public int longestSubstringWithKDistinctChars(String s, int k) {

		if (s == null || s.length() == 0 || k == 0)
			return 0;
		int windowStart = 0;
		int maxLen = 0;
		Map<Character, Integer> mp = new HashMap<>();

		for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
			char right = s.charAt(windowEnd);
			mp.put(right, mp.getOrDefault(right, 0) + 1);

			while (mp.size() > k) {
				char left = s.charAt(windowStart);
				mp.put(left, mp.get(left) - 1);
				if (mp.get(left) == 0) {
					mp.remove(left);
				}
				windowStart++;
			}
			maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
			log.info("current max sub string: {}", s.substring(windowStart, windowEnd));
		}
		return maxLen;
	}

	public static void main(String[] args) {
		LongestKthDistinctSubString lss = new LongestKthDistinctSubString();

		String test = "aaadddbbddccccc";
		int k = 3;

		int length = lss.longestSubstringWithKDistinctChars(test, k);

		log.info("Max substring length is: {}", length);
	}

}
