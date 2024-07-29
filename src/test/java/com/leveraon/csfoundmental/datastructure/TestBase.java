package com.leveraon.csfoundmental.datastructure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestBase {

	@BeforeEach
	public void setUp() {
		log.info("-------------------------------start");
	}

	@AfterEach
	public void tearDown() {
		log.info("-------------------------------end");
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
