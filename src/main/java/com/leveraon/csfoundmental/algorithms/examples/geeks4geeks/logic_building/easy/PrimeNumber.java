package com.leveraon.csfoundmental.algorithms.examples.geeks4geeks.logic_building.easy;

/**
 * <h2>Given a positive integer, check if the number is prime or not. A prime is
 * a natural number greater than 1 that has no positive divisors other than 1
 * and itself. Examples of the first few prime numbers are {2, 3, 5,
 * ...}</h2><br>
 * Input: n = 11<br>
 * Output: true<br>
 * 
 * Input: n = 15<br>
 * Output: false<br>
 * 
 * Input: n = 1<br>
 * Output: false <br>
 */
public class PrimeNumber {

	/**
	 * Time complexity: O(n) Auxiliary Space: O(1)
	 * 
	 * @param number
	 * @return
	 */
	static boolean primeNumber(int number) {

		if (number == 1 || number == 2) {
			return true;
		}

		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				System.out.println("Number (" + number + ") is not prime, can be divided by " + i);
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		int test = 422231113;

		System.out.println("Number (" + test + ") is prime:  " + primeNumber(test));
	}
}
