package com.leveraon.csfoundmental.datastructure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {

	private static final Logger LOG = LoggerFactory.getLogger(TestBase.class);

	@BeforeEach
	public void setUp() {
		LOG.info("-------------------------------start");
	}

	@AfterEach
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
