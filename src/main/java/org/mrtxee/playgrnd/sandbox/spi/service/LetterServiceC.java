package org.mrtxee.playgrnd.sandbox.spi.service;

import org.mrtxee.playgrnd.sandbox.spi.api.LetterService;

public class LetterServiceC implements LetterService {
  @Override
  public String getLetter() {
    return "ccccc";
  }
}
