/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples.stacks;

import com.leveraon.csfoundmental.algorithms.utils.RandomIntArrayGenerator;
import com.leveraon.csfoundmental.datastructure.stacks.ArrayStack;
import com.leveraon.csfoundmental.datastructure.stacks.Stack;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
public class ReserveArrayWithStack {

	public static int[] reverse(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		Stack<Integer> intStack = new ArrayStack<Integer>(array.length);
		for (int i = 0; i < array.length; i++) {
			intStack.push(array[i]);
		}

		int index = 0;
		while (!intStack.isEmpty()) {
			array[index] = intStack.pop();
			index++;
		}

		return array;
	}

	public static void main(String[] args) {
		int[] array = RandomIntArrayGenerator.generateRandomIntArray(5, 1, 50);
		log.info("Array before reverse {}", array);
		log.info("Array after reverse {}", reverse(array));
	}
}
