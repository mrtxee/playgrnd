package org.mrtxee.playgrnd.ti.log.d20260403.api;

import org.mrtxee.playgrnd.ti.log.d20260403.dto.WithdrawalDto;import org.mrtxee.playgrnd.ti.log.d20260403.enums.Currency;public interface AtmService {

  WithdrawalDto withdraw(Integer amount, Currency currency);

}
