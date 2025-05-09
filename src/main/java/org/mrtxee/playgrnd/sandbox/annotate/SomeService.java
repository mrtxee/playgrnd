package org.mrtxee.playgrnd.sandbox.annotate;

import java.text.MessageFormat;
import org.mrtxee.playgrnd.sandbox.annotate.annotaion.Axn;

public class SomeService {
  @Axn
  public static String getMe(String name) {
    return MessageFormat.format("I`ve got response from {0}", name);
  }
}
