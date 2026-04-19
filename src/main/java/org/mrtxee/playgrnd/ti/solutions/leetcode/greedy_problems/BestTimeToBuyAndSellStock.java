package org.mrtxee.playgrnd.ti.solutions.leetcode.greedy_problems;


/**
 * 121. Best Time to Buy and Sell Stock, easy
 * <a href="https://leetcode.com/problems/valid-parentheses/">link</a>
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a
 * different day in the future to sell that stock. Return the maximum profit you can achieve from
 * this transaction. If you cannot achieve any profit, return 0.
 * Eg.:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 */
public class BestTimeToBuyAndSellStock {

  public static void main(String[] args) {
    int[] prices = new int[] {7, 1, 5, 3, 6, 4};
    System.out.println(5 == maxProfit(prices));
  }

  public static int maxProfit(int[] prices) {

    int minPrice = Integer.MAX_VALUE;
    int maxProfit = 0;

    for (int currentDayPrice : prices) {
      if (minPrice > currentDayPrice) {
        minPrice = currentDayPrice;
      } else {
        int todaySellProfit = currentDayPrice - minPrice;
        if (todaySellProfit > maxProfit) {
          maxProfit = todaySellProfit;
        }
      }
    }
    return maxProfit;
  }

  /**
   * Complexity:
   *   - Time  = O(N)
   *   - Space = O(1)
   */
}
