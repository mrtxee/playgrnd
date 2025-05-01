package org.mrtxee.playgrnd.sandbox.annotate;

import java.text.MessageFormat;
import org.mrtxee.playgrnd.sandbox.annotate.annotaion.AxReflect;

public class SomeService {
  @AxReflect
  public static String getMe(String name) {
    return MessageFormat.format("I`ve got response from {0}", name);
  }
}
