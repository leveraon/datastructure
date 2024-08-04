package com.leveraon.csfoundmental.algorithms.examples;

import java.util.ArrayList;

/**
 * Given a list of numbers and a target number, write a program to determine
 * whether
 * the target number can be calculated by applying + - * /
 * operations
 * to the
 * number
 * list?
 * You can assume () is automatically added when necessary.
 * For example, given 1,2,3,4 and 21, return true. Because (1+2)*(3+4)=21
 */
public class GetTargetNumber {

    public static boolean isReachable(ArrayList<Integer> list, int target) {
        if (list == null || list.size() == 0)
            return false;
        int i = 0;
        int j = list.size() - 1;
        ArrayList<Integer> results = getResults(list, i, j, target);
        for (int num : results) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Integer> getResults(ArrayList<Integer> list,
            int left, int right, int target) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (left > right) {
            return result;
        } else if (left == right) {
            result.add(list.get(left));
            return result;
        }
        for (int i = left; i < right; i++) {
            ArrayList<Integer> result1 = getResults(list, left, i, target);
            ArrayList<Integer> result2 = getResults(list, i + 1, right, target);
            for (int x : result1) {
                for (int y : result2) {
                    result.add(x + y);
                    result.add(x - y);
                    result.add(x * y);
                    if (y != 0)
                        result.add(x / y);
                }
            }
        }
        return result;
    }
}
