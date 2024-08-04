package com.leveraon.csfoundmental.algorithm.recursion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecursionFactorialFunction {
    public static int factorial(int n) throws IllegalArgumentException {
        if (n < 0)
            throw new IllegalArgumentException(); // argument must be nonnegative
        else if (n == 0)
            return 1; // base case
        else
            return n * factorial(n - 1); // recursive case
    }

    public static void main(String[] args) {
        int factorial = 10;

        log.info("Factorial Function for {}, is {}", factorial, factorial(factorial));
    }
}
