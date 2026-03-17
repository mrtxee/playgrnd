package org.mrtxee.playgrnd.sandbox.inheritance;

public class Main {

  public static void main(String[] args) {
    test(Dad.class);
    test(Grandpa.class);
  }
  public static void test(Class<? extends Grandpa> clazz) {
    System.out.println(clazz.getSimpleName());
  }

  public interface Grandpa {
  }

  public interface Dad extends Grandpa{
  }
}
