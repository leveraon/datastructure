package com.leveraon.csfoundmental.algorithms.examples.geeks4geeks.logic_building.easy;

/**
 * <h2>Given an Integer n, find the reverse of its digits.</h2> <br>
 * Input: n = 122<br>
 * Output: 221<br>
 * Explanation: By reversing the digits of number, number will change into
 * 221.<br>
 * 
 * Input: n = 200<br>
 * Output: 2<br>
 * Explanation: By reversing the digits of number, number will change into
 * 2.<br>
 * 
 * Input: n = 12345 <br>
 * Output: 54321<br>
 * Explanation: By reversing the digits of number, number will change into
 * 54321.<br>
 */
public class ReverseDigitsOfNumber {

	// split number with key value pair eg: 123
	// value mod 10, if value not 0, sum = sum + result*10

	static int reverseDigitsMine(int number) {
		int sum = 0;
		int mod = number % 10;
		int trimmedNumber = number;
		// trim
		while (mod == 0) {
			trimmedNumber = trimmedNumber / 10;
			mod = trimmedNumber % 10;
		}

		int quote = trimmedNumber / 10;

		if (quote == 0) {
			sum = trimmedNumber;
			return sum;
		}

		int nextDigit = trimmedNumber % 10;
		while (quote != 0) {
			sum = (sum + nextDigit) * 10;
			nextDigit = quote % 10;
			quote = quote / 10;
		}

		if (nextDigit != 0) {
			sum += nextDigit;
		}

		return sum;
	}

	/**
	 * Time Complexity - O(log n) Space Complexity - O(1)
	 * 
	 * @param n
	 * @return
	 */
	static int reverseDigits(int n) {
		int revNum = 0;
		while (n > 0) {
			revNum = revNum * 10 + n % 10;
			n = n / 10;
		}
		return revNum;
	}

	public static void main(String[] args) {
		int number1 = 1023000;
		int number2 = 1;
		int number3 = 10339;
		int number4 = 100002;

		System.out.println(reverseDigits(number1));
		System.out.println(reverseDigits(number2));
		System.out.println(reverseDigits(number3));
		System.out.println(reverseDigits(number4));

		System.out.println("-----------------------");

		System.out.println(reverseDigitsMine(number1));
		System.out.println(reverseDigitsMine(number2));
		System.out.println(reverseDigitsMine(number3));
		System.out.println(reverseDigitsMine(number4));
	}
}
