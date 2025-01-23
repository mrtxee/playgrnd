package org.mrtxee.playgrnd.sandbox.spi.service;

import org.mrtxee.playgrnd.sandbox.spi.api.LetterService;

public class LetterServiceA implements LetterService {
  @Override
  public String getLetter() {
    return "aaa";
  }
}
