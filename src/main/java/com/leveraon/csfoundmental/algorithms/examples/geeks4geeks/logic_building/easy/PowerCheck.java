package com.leveraon.csfoundmental.algorithms.examples.geeks4geeks.logic_building.easy;

/**
 * Given two positive numbers x and y, check if y is a power of x or not. <br>
 * Input: x = 10, y = 1<br>
 * Output: True<br>
 * x^0 = 1<br>
 * 
 * Input: x = 10, y = 1000<br>
 * Output: True<br>
 * x^3 = 1<br>
 * 
 * Input: x = 10, y = 1001<br>
 * Output: False<br>
 */
public class PowerCheck {

	static boolean powerCheck(int x, int y) {

		if (y == 1)
			return true;

		int power = 1;
		int origin = y;
		while (y != 0) {
			if (y == x) {
				System.out.println(origin + " can be calculated by " + x + " power of " + power);
				return true;
			}

			y = y / x;
			power = power + 1;
		}

		return false;
	}

	/**
	 * Time complexity: O(Logxy) Auxiliary space: O(1)
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	static boolean isPower(int x, long y) {
		// The only power of 1 is 1 itself
		if (x == 1)
			return (y == 1);

		// Repeatedly compute power of x
		long pow = 1;
		while (pow < y)
			pow *= x;

		// Check if power of x becomes y
		return (pow == y);
	}

	/**
	 * Time complexity: O(1) Auxiliary space: O(1)
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	static boolean isPower(int x, int y) {
		double res1 = Math.log(y) / Math.log(x);
		return res1 == Math.floor(res1);
	}

	public static void main(String[] args) {
		int x = 2, y = 2048;

		System.out.println("Power check x: " + x + " y:" + y + " , result is : " + powerCheck(x, y));
	}
}
