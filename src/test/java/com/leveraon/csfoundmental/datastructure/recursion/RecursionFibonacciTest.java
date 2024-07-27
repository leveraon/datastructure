package com.leveraon.csfoundmental.datastructure.recursion;

import org.junit.jupiter.api.Test;

import com.leveraon.csfoundmental.datastructure.TestBase;

public class RecursionFibonacciTest extends TestBase {

	@Test
	public void testFibonacci() {
		printArray(RecursionFibonacci.fibonacci(20));
	}
}
