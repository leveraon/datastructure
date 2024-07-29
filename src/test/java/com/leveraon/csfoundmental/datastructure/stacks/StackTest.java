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
