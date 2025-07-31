package com.leveraon.csfoundmental.algorithms.examples.recursion;

import com.leveraon.csfoundmental.datastructure.stacks.LinkedStack;
import com.leveraon.csfoundmental.datastructure.stacks.Stack;

import lombok.extern.slf4j.Slf4j;

/**
 * Given an input string, reverse the string word by word. A word is defined as
 * a sequence
 * of non-space characters.
 * The input string does not contain leading or trailing spaces and the words
 * are always
 * separated by a single space.
 * For example, Given s = "the sky is blue", return "blue is sky the".
 * Could you do it in-place without allocating extra space?
 */
@Slf4j
public class ReverseWords {
    public static String reverseWords(char[] s) {
        int i = 0;
        for (int j = 0; j < s.length; j++) {
            if (s[j] == ' ') {
                reverse(s, i, j - 1);
                i = j + 1;
            }
        }
        reverse(s, i, s.length - 1);
        reverse(s, 0, s.length - 1);

        return new String(s);
    }

    public static void reverse(char[] s, int i, int j) {
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }

    /** Reverses the contents of subarray data[low] through data[high] inclusive. */
    public static void reverseArray(int[] data, int low, int high) {
        if (low < high) { // if at least two elements in subarray
            int temp = data[low]; // swap data[low] and data[high]
            data[low] = data[high];
            data[high] = temp;
            reverseArray(data, low + 1, high - 1); // recur on the rest
        }
    }

    public static String reverseWords(String words) {
        Stack<String> stack = new LinkedStack<>();
        if (words == null) {
            return null;
        }
        String[] wordArray = words.split(" ");
        for (String word : wordArray) {
            stack.push(word);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
            if (stack.size() > 0) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String words = "rolling in the deep";
        log.info("Original array:: {}", words);
        log.info("Reversed array:: {}", reverseWords(words.toCharArray()));
        log.info("Reversed array with queue:: {}", reverseWords(words));
    }
}
