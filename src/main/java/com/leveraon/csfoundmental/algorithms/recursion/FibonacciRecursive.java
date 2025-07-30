package com.leveraon.csfoundmental.algorithms.recursion;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FibonacciRecursive {

	public static Double[] fibonacci(Double n) {
		log.info("Current n : " + n);
		if (n <= 1) {
			Double[] result = { n, Double.valueOf(0) };

			return result;
		} else {
			Double[] next = fibonacci(n - 1);
			Double[] answer = { next[1] + next[0], next[0] };
			log.info("next : " + Arrays.toString(next));
			return answer;
		}
	}

	public static void main(String[] args) {
		Double fibonacci = Double.valueOf(50);
		fibonacci(fibonacci);
	}
}
