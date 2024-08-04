package com.leveraon.csfoundmental.datastructure.examples;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BestTimeToSellStock {

    /**
     * Say you have an array for which the ith element is the price of a given stock
     * on day i.
     * If you were only permitted to complete at most one transaction (ie, buy one
     * and sell
     * one share of the stock), design an algorithm to find the maximum profit.
     */
    public static int maxProfitOne(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int min = prices[0]; // min so far
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result = Math.max(result, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return result;
    }

    /**
     * Say you have an array for which the ith element is the price of a given stock
     * on day i.
     * Design an algorithm to find the maximum profit. You may complete as many
     * transactions
     * as you like (ie, buy one and sell one share of the stock multiple times).
     * However,
     * you may not engage in multiple transactions at the same time (ie, you must
     * sell
     * the stock before you buy again).
     * 
     * @param args
     */
    public static int maxProfitTwo(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            if (diff > 0) {
                profit += diff;
            }
        }
        return profit;
    }

    /**
     * Say you have an array for which the ith element is the price of a given stock
     * on day i.
     * Design an algorithm to find the maximum profit. You may complete at most two
     * transactions.
     * Note: A transaction is a buy & a sell. You may not engage in multiple
     * transactions
     * at the same time (ie, you must sell the stock before you buy again).
     * 
     * @param args
     */
    public static int maxProfitThree(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        // highest profit in 0 ... i
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];
        // DP from left to right
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }
        // DP from right to left
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, left[i] + right[i]);
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] prices = { 12, 33, 30, 39, 13, 22, 13, 29, };
        log.info("Max profit solution one: {}", maxProfitOne(prices));

        log.info("Max profit solution two: {}", maxProfitTwo(prices));

        log.info("Max profit solution three: {}", maxProfitThree(prices));
    }
}
