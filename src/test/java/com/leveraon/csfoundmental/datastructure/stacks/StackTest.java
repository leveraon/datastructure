package com.leveraon.csfoundmental.datastructure.stacks;

import org.junit.jupiter.api.Test;

import com.leveraon.csfoundmental.datastructure.TestBase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StackTest extends TestBase {

    /** A generic method for reversing an array. */
    public static <E> void reverse(E[] a) {
        Stack<E> buffer = new ArrayStack<>(a.length);
        for (int i = 0; i < a.length; i++)
            buffer.push(a[i]);
        for (int i = 0; i < a.length; i++)
            a[i] = buffer.pop();
    }

    /** Tests if delimiters in the given expression are properly matched. */
    public static boolean isMatched(String expression) {
        final String opening = "({["; // opening delimiters
        final String closing = ")}]"; // respective closing delimiters
        Stack<Character> buffer = new LinkedStack<>();
        for (char c : expression.toCharArray()) {
            if (opening.indexOf(c) != -1) // this is a left delimiter
                buffer.push(c);
            else if (closing.indexOf(c) != -1) { // this is a right delimiter
                if (buffer.isEmpty()) // nothing to match with
                    return false;
                if (closing.indexOf(c) != opening.indexOf(buffer.pop()))
                    return false; // mismatched delimiter
            }
        }
        return buffer.isEmpty(); // were all opening delimiters matched?
    }

    /** Tests if every opening tag has a matching closing tag in HTML string. */
    public static boolean isHTMLMatched(String html) {
        Stack<String> buffer = new LinkedStack<>();
        int j = html.indexOf('<'); // find first ’<’ character (if any)
        while (j != -1) {
            int k = html.indexOf('>', j + 1); // find next ’>’ character
            if (k == -1)
                return false; // invalid tag
            String tag = html.substring(j + 1, k); // strip away < >
            if (!tag.startsWith("/")) // this is an opening tag
                buffer.push(tag);
            else { // this is a closing tag
                if (buffer.isEmpty())
                    return false; // no tag to match
                if (!tag.substring(1).equals(buffer.pop()))
                    return false; // mismatched tag
            }
            j = html.indexOf('<', k + 1); // find next ’<’ character (if any)
        }
        return buffer.isEmpty(); // were all opening tags matched?
    }

    /**
     * Testing reverse array using a stack
     */
    @Test
    public void testReverseArray() {

    }

    public static void main(String[] args) {
        Integer[] a = { 4, 8, 15, 16, 23, 42 };

        log.info("Original array {}", a);
        reverse(a);
        log.info("Reversed array {}", a);
    }

}
