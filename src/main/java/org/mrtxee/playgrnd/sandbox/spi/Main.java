package org.mrtxee.playgrnd.sandbox.spi;

import java.util.ServiceLoader;
import org.mrtxee.playgrnd.sandbox.spi.api.LetterService;

public class Main {
  public static void main(String[] args) {
    ServiceLoader<LetterService> serviceLoader = ServiceLoader.load(LetterService.class);
    for (LetterService providedService : serviceLoader){
      System.out.println(providedService.getClass().getSimpleName()+" says : "+providedService.getLetter());
    }
  }
}
