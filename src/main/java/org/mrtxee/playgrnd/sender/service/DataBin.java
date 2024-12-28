package org.mrtxee.playgrnd.sender.service;

import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class DataBin {
  private final Random random = new Random();

  public String getFromBin() {
    return "bin gives you " + random.nextInt() + " data pieces";
  }

  public int getAge() {
    return random.nextInt(0, 700);
  }
}
