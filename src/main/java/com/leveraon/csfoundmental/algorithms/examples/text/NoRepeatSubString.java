/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples.text;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * <h2>Given a string, find the length of the longest substring which has no
 * repeating characters.</h2>
 * 
 * 
 * <p>
 * Input: String="aabccbb" <br/>
 * Output: 3 <br/>
 * Explanation: The longest substring without any repeating characters is "abc".
 * 
 * </p>
 */
@Slf4j
public class NoRepeatSubString {

	public Map<Character, Integer> lengthOfLongestSubstring(String s) {
		int windowStart = 0;
		int maxLen = 0;
		Map<Character, Integer> mp = new HashMap<>();
		for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
			char rightChar = s.charAt(windowEnd);
			if (mp.containsKey(rightChar)) {
				windowStart = Math.max(windowStart, mp.get(rightChar) + 1);
			}
			mp.put(rightChar, windowEnd);
			maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
		}
		log.info("Max length is {}", maxLen);
		return mp;
	}

	public static void main(String[] args) {
		NoRepeatSubString nrs = new NoRepeatSubString();

		String test = "abcdabcaabcdeabcdabcdef";

		Map<Character, Integer> result = nrs.lengthOfLongestSubstring(test);

		log.info("Result is {}", result);
	}

}
