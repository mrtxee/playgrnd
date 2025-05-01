package org.mrtxee.playgrnd.sandbox.annotate;

import java.lang.reflect.Method;
import org.mrtxee.playgrnd.sandbox.annotate.annotaion.AxReflect;

public class ReflectAnnotationProcessor {
  public static void main(String[] args) throws Exception {
    Main myClass = new Main();

    // Получаем все методы класса
    Method[] methods = Main.class.getDeclaredMethods();

    for (Method method : methods) {
      // Проверяем, имеет ли метод аннотацию @AxNotated
      if (method.isAnnotationPresent(AxReflect.class)) {
        // Получаем параметры метода
        Class<?>[] parameterTypes = method.getParameterTypes();

        // Создаем массив для аргументов
        Object[] argsToPass = new Object[parameterTypes.length];

        // Заполняем массив аргументов (можно использовать любые значения)
        for (int i = 0; i < parameterTypes.length; i++) {
          if (parameterTypes[i] == String.class) {
            argsToPass[i] = "Neo"; // Пример строки
          } else if (parameterTypes[i] == int.class) {
            argsToPass[i] = 42; // Пример целого числа
          }
          // Добавьте дополнительные условия для других типов, если необходимо
        }

        // Вызываем метод и получаем результат
        String originalResult = (String) method.invoke(myClass, argsToPass);

        // Изменяем результат
        String modifiedResult = modifyResult(originalResult);

        System.out.println(modifiedResult);
      }
    }
  }

  private static String modifyResult(String original) {
    // Здесь вы можете изменить результат по своему усмотрению
    return "Modified: " + original;
  }
}