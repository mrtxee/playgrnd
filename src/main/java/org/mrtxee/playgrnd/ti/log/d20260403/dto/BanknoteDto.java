package org.mrtxee.playgrnd.ti.log.d20260403.dto;

import lombok.AllArgsConstructor;
import lombok.Data;import org.mrtxee.playgrnd.ti.log.d20260403.enums.Currency;

@Data
@AllArgsConstructor
public class BanknoteDto {
  int nominal;
  Currency currency;
}
