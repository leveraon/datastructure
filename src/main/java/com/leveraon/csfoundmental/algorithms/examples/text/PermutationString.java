package com.leveraon.csfoundmental.algorithms.examples.text;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Given two strings s1 and s2, return true if s2 contains the permutation of
 * s1. <br/>
 * In other words, one of s1's permutations is the substring of s2.
 * </p>
 */
@Slf4j
public class PermutationString {

	public boolean checkInclusion(String s1, String s2) {
		if (s1.length() > s2.length())
			return false;
		int[] s1map = new int[26];
		int[] s2map = new int[26];
		for (int i = 0; i < s1.length(); i++) {
			s1map[s1.charAt(i) - 'a']++;
			s2map[s2.charAt(i) - 'a']++;
		}
		int count = 0;
		for (int i = 0; i < 26; i++)
			if (s1map[i] == s2map[i])
				count++;
		for (int i = 0; i < s2.length() - s1.length(); i++) {
			int right = s2.charAt(i + s1.length()) - 'a';
			int left = s2.charAt(i) - 'a';
			if (count == 26)
				return true;
			s2map[right]++;
			if (s2map[right] == s1map[right])
				count++;
			else if (s2map[right] == s1map[right] + 1)
				count--;
			s2map[left]--;
			if (s2map[left] == s1map[left])
				count++;
			else if (s2map[left] == s1map[left] - 1)
				count--;
		}
		return count == 26;
	}

	public static void main(String[] args) {
		PermutationString demo = new PermutationString();
		String s1 = "helloworld";
		String s2 = "dontsayhelloworldatfirsttime";

		boolean result = demo.checkInclusion(s1, s2);

		log.info("result is {}", result);
	}
}
