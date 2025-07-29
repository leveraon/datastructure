/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples;

import java.util.HashMap;

import com.leveraon.csfoundmental.datastructure.stacks.ArrayStack;
import com.leveraon.csfoundmental.datastructure.stacks.Stack;

/**
 * 
 */
public class BalancedBrackets {
	/**
	 * Checks if the given string has balanced brackets. Uses a Stack to keep track
	 * of opening brackets.
	 *
	 * @param s The input string containing brackets.
	 * @return true if the brackets are balanced, false otherwise.
	 */
	public boolean isBalanced(String s) {
		// Use a HashMap to store the relationships between closing and opening
		// brackets.
		// This makes the matching logic cleaner and more extensible.
		HashMap<Character, Character> bracketPairs = new HashMap<>();
		bracketPairs.put(')', '(');
		bracketPairs.put(']', '[');
		bracketPairs.put('}', '{');

		Stack<Character> stack = new ArrayStack<Character>();

		for (char c : s.toCharArray()) {
			// Case 1: If it's an opening bracket, push it onto the stack.
			if (c == '(' || c == '[' || c == '{') {
				stack.push(c);
			}
			// Case 2: If it's a closing bracket.
			else if (c == ')' || c == ']' || c == '}') {
				// If the stack is empty, it means a closing bracket appeared
				// without a corresponding opening one.
				if (stack.isEmpty()) {
					return false;
				}

				// Get the expected opening bracket for the current closing bracket.
				char expectedOpenBracket = bracketPairs.get(c);
				// Pop the top of the stack (the most recently seen opening bracket).
				char actualOpenBracket = stack.pop();

				// If the popped bracket doesn't match the expected one, it's unbalanced.
				if (actualOpenBracket != expectedOpenBracket) {
					return false;
				}
			}
			// Case 3: Ignore other characters if they appear.
			// If the problem strictly says only brackets, this else block can be omitted
			// or could throw an error if unexpected characters are invalid.
			// else {
			// // Handle other characters if necessary, e.g., throw IllegalArgumentException
			// }
		}

		// After iterating through the entire string, if the stack is empty,
		// all brackets were balanced. Otherwise, there are unclosed opening brackets.
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		BalancedBrackets solver = new BalancedBrackets();

		// Test Cases
		System.out.println("()[]{} -> " + solver.isBalanced("()[]{}")); // true
		System.out.println("([{}]) -> " + solver.isBalanced("([{}])")); // true
		System.out.println("({[({})]}) -> " + solver.isBalanced("({[({})]})")); // true
		System.out.println("( -> " + solver.isBalanced("(")); // false
		System.out.println(")] -> " + solver.isBalanced(")]")); // false
		System.out.println("([)] -> " + solver.isBalanced("([)]")); // false
		System.out.println("{[} -> " + solver.isBalanced("{[}")); // false
		System.out.println("{ -> " + solver.isBalanced("{")); // false
		System.out.println("] -> " + solver.isBalanced("]")); // false
		System.out.println(" -> " + solver.isBalanced("")); // true (empty string is balanced)
		System.out.println("abc(def)ghi -> " + solver.isBalanced("abc(def)ghi")); // true (ignores non-brackets)
	}
}
