package org.mrtxee.playgrnd.app.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AxNotatedAspect {

  @Around("@annotation(AxNotated)")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    // Вызываем оригинальный метод и получаем результат
    Object result = joinPoint.proceed();
    // Изменяем результат
    return modifyResult(result);
  }

  private Object modifyResult(Object original) {
    // Здесь вы можете изменить результат по своему усмотрению
    if (original instanceof String) {
      return "Modified by annotation processing aspect: " + original;
    }
    return original; // Возвращаем оригинал, если не строка
  }
}