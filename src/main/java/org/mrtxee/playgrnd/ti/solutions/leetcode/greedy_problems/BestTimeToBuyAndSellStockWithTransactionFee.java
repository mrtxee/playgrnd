package org.mrtxee.playgrnd.ti.solutions.leetcode.greedy_problems;


/**
 * 714. Best Time to Buy and Sell Stock with Transaction Fee, medium
 * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/">link</a>
 * You are given an array prices where prices[i] is the price of a given stock on the ith day, and
 * an integer fee representing a transaction fee. Find the maximum profit you can achieve. You may
 * complete as many transactions as you like, but you need to pay the transaction fee for each
 * transaction.
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {

  public static void main(String[] args) {
    int[] prices = new int[] {1, 3, 2, 8, 4, 9};
    int fee = 2;
    System.out.println(8 == maxProfit(prices, fee));
  }

  /**
   * Алгоритм использует метод динамического программирования с двумя состояниями (buy / sell),
   * который на каждом шаге решает: выгоднее ли совершить действие сегодня или сохранить
   * предыдущее оптимальное состояние. Комиссия учитывается только при продаже, что автоматически
   * отсекает частые мелкие сделки.
   */
  public static int maxProfit(int[] prices, int fee) {

    // Состояние "держим акцию". -∞ гарантирует, что первая реальная покупка корректно перезапишет значение.
    int buy = Integer.MIN_VALUE;
    // Состояние "не держим акцию". Изначально прибыль 0, так как сделок ещё не было.
    int sell = 0;

    // Проходим по каждому дню торгов, последовательно пересчитывая оптимальные состояния
    for (int price : prices) {

      // Выбираем макс. выгоду: оставить старую позицию ИЛИ купить сегодня (кэш sell минус цена акции).
      // buy хранит не реальные деньги, а "виртуальный баланс" после покупки.
      buy = Math.max(buy, sell - price);

      // Выбираем макс. выгоду: оставить старую позицию ИЛИ продать сегодня (баланс buy + цена продажи - комиссия).
      // Обновление buy перед sell в одной итерации безопасно: мгновенная покупка+продажа в один день даст -fee,
      // поэтому Math.max автоматически проигнорирует убыточную операцию и оставит предыдущее sell.
      sell = Math.max(sell, buy + price - fee);
    }

    // В конце всегда оптимально находиться в состоянии "не держим акцию", т.к. незакрытая позиция не даёт прибыли.
    return sell;

  }
}

/**
 * Complexity:
 * - Time  = O(N)
 * - Space = O(1)
 */

