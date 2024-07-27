package com.leveraon.csfoundmental.datastructure.recursion;

import org.junit.jupiter.api.Test;

import com.leveraon.csfoundmental.datastructure.TestBase;

public class RecursionRulerTest extends TestBase {

	@Test
	public void testDrawRuler() {
		int inches = 3, majorLength = 5;
		RecursionRuler.drawRuler(inches, majorLength);
	}
}
