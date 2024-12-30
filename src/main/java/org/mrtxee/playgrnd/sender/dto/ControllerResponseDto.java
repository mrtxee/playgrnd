package org.mrtxee.playgrnd.sender.dto;

import java.util.Date;

public record ControllerResponseDto(
  Date time,
  String response

) {
  public ControllerResponseDto(String response) {
    this(new Date(), response);
  }
}
