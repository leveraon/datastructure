package com.leveraon.csfoundmental.datastructure.recursion;

import org.junit.jupiter.api.Test;

import com.leveraon.csfoundmental.datastructure.TestBase;

public class RecursionEscaltorTest extends TestBase {

	@Test
	public void testRecursionEscaltor() {
		RecursionEscalator.drawEscalator(10);
	}
	
	@Test
	public void testStairCase() {
		RecursionEscalator.drawStairCase(10);
	}
}
