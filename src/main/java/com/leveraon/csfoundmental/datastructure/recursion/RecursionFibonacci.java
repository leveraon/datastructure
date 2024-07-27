package com.leveraon.csfoundmental.datastructure.recursion;

import java.util.Arrays;

public class RecursionFibonacci {

	public static Integer[] fibonacci(int n) {
		System.out.println("Current n : " + n);
		if (n <= 1) {
			Integer[] result = { n, 0 };

			return result;
		} else {
			Integer[] next = fibonacci(n - 1);
			Integer[] result = { next[1] + next[0], next[0] };
			System.out.println("next : " + Arrays.toString(next));
			return result;
		}
	}
}
