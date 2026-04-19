package org.mrtxee.playgrnd.ti.solutions.leetcode.greedy_problems;


/**
 * 122. Best Time to Buy and Sell Stock II, medium
 * <a href="https://leetcode.com/problems/valid-parentheses/">link</a>
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith
 * day. On each day, you may decide to buy and/or sell the stock. You can only hold at most one
 * share of the stock at any time. However, you can sell and buy the stock multiple times on the
 * same day, ensuring you never hold more than one share of the stock.
 * Eg.:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 */
public class BestTimeToBuyAndSellStockII {

  public static void main(String[] args) {
    int[] prices = new int[] {7, 1, 5, 3, 6, 4};
    System.out.println(7 == maxProfit(prices));
  }

  /**
   * Если вчера была цена хорошая, то проводим сделку
   */
  public static int maxProfit(int[] prices) {

    int totalProfit = 0;

    for (int i = 1; i < prices.length; i++) {
      int todayPrice = prices[i];
      int yesterdayPrice = prices[i - 1];
      int margin = todayPrice - yesterdayPrice;
      if (margin > 0) {
        totalProfit += margin;
      }
    }
    return totalProfit;
  }

  /**
   * Complexity:
   *   - Time  = O(N)
   *   - Space = O(1)
   */
}
