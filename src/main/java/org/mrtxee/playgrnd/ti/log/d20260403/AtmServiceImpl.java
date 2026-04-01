package org.mrtxee.playgrnd.ti.log.d20260403;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mrtxee.playgrnd.ti.log.d20260403.api.AtmService;
import org.mrtxee.playgrnd.ti.log.d20260403.dto.BanknoteDto;
import org.mrtxee.playgrnd.ti.log.d20260403.dto.WithdrawalDto;
import org.mrtxee.playgrnd.ti.log.d20260403.enums.Currency;

/**
 * YANDEX TI
 *
 * Банкомат.
 * Инициализируется набором купюр и умеет выдавать купюры для заданной суммы, либо отвечать отказом.
 * При выдаче купюры списываются с баланса банкомата.
 * Допустимые номиналы:
 * -  50₽, 100₽, 500₽, 1000₽, 5000₽.
 * -  20 EUR, 100 EUR, 500 EUR
 *
 * Каждая валюта обрабатывается отдельно, обмен валют банкоматом не поддерживается.
 *
 * Банкомат может использоваться многопоточно (например, резервирование выдачи денег из приложения).
 * Поддержку многопоточности можно вынести в отдельную итерацию.
 */

// TODO: make me singleton service
public class AtmServiceImpl implements AtmService {

  private final Map<BanknoteDto, Integer> moneyLeft;

  public AtmServiceImpl(Map<BanknoteDto, Integer> bills) {
    moneyLeft = bills;
  }

  @Override
  public synchronized WithdrawalDto withdraw(final Integer amountAsked, Currency currency) {

    List<Map.Entry<BanknoteDto, Integer>> banknotesAvailable = moneyLeft.entrySet().stream().filter(
            banknoteDtoIntegerEntry -> banknoteDtoIntegerEntry.getKey().getCurrency() == currency)
        .sorted((o1, o2) -> Integer.compare(o2.getKey().getNominal(), o1.getKey().getNominal()))
        .toList();

    int remaining = amountAsked;
    Map<Integer, Integer> takingBanknotes = new HashMap<>();
    for (Map.Entry<BanknoteDto, Integer> banknote : banknotesAvailable) {
      int take = Math.min(remaining / banknote.getKey().getNominal(), banknote.getValue());
      takingBanknotes.put(banknote.getKey().getNominal(), take);
      remaining = remaining - take * banknote.getKey().getNominal();
    }

    if (remaining == 0) {
      takingBanknotes.forEach((nominal, count) -> {
        final BanknoteDto banknoteKey = new BanknoteDto(nominal, currency);
        moneyLeft.put(banknoteKey, moneyLeft.get(banknoteKey) - count);
      });
      return new WithdrawalDto(takingBanknotes);
    } else {
      throw new RuntimeException("Asked amount is not available");
    }
  }

}
