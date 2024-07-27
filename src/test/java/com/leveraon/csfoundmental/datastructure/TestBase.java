package com.leveraon.csfoundmental.datastructure;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {

	private static final Logger LOG = LoggerFactory.getLogger(TestBase.class);

	@Before
	public void setUp() {
		LOG.info("-------------------------------start");
	}

	@After
	public void tearDown() {
		LOG.info("-------------------------------end");
	}

	public <T> void printArray(T[] array) {
		System.out.print("[");
		for (T element : array) {
			System.out.print(element);
			System.out.print(",");
		}
		System.out.print("]");
	}

}
