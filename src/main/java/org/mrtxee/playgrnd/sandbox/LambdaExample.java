package org.mrtxee.playgrnd.sandbox;

import java.util.Arrays;
import java.util.List;

public class LambdaExample {

  public static void main(String[] args) {
    process();
  }

  public static void process() {
    String prefix = "User: ";           // effectively final
    List<String> names = Arrays.asList("Alice", "Bob");

    // Можно использовать prefix в лямбде
    names.forEach(name -> System.out.println(prefix + name));

    //prefix = "New: "; // Если раскомментировать - лямбда перестанет компилироваться!
  }
}