package org.mrtxee.playgrnd.sandbox.annotate;

import java.text.MessageFormat;
import org.mrtxee.playgrnd.sandbox.annotate.annotaion.Axn;

;

public class Main {

  public static void main(String[] args) {
    System.out.println(getMe("Neo"));
    SomeService ss = new SomeService();
    System.out.println(ss.getMe("Trinity"));
  }

  @Axn
  public static String getMe(String name) {
    return MessageFormat.format("I`ve got response from {0}", name);
  }

}

