package com.leveraon.csfoundmental.datastructure.recursion;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecursionFibonacci {

	public static Integer[] fibonacci(int n) {
		log.info("Current n : " + n);
		if (n <= 1) {
			Integer[] result = { n, 0 };

			return result;
		} else {
			Integer[] next = fibonacci(n - 1);
			Integer[] result = { next[1] + next[0], next[0] };
			log.info("next : " + Arrays.toString(next));
			return result;
		}
	}

	public static void main(String[] args) {
		int fibonacci = 110;
		fibonacci(fibonacci);
	}
}
