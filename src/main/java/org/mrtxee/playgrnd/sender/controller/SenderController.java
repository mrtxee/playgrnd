package org.mrtxee.playgrnd.sender.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrtxee.playgrnd.sender.service.RequestInvoker;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SenderController {
  private final RequestInvoker requestInvoker;

  @PostConstruct
  private void initialSend() {
    log.debug("initialSend");

    /**
     * Задача:
     *  - Имплементировать интерфейс в стиле билдер для генерации запросов во внешнюю систему
     *
     * Ограничения:
     *  - Для каждого запроса создается экземпляр pojo-класса SenderImpl<R>. Это связано с разнообразием запросов и
     *  сложностью их настройки.
     *  - <R> - тип возвращаемого ответа.
     *  - Интерфейс запроса - org.mrtxee.playgrnd.sender.api.Sender
     *
     *  Проблема, которую предстоит решить:
     *  Для того, чтобы SenderImpl<R> смог определить тип дженерика, приходится спускать тип в
     *  requestInvoker, который в последствии нигде не используется. Не хотелось бы этого делать.
     *
     *  Как заменить строку SenderController:38 на String response = RequestInvoker.invoke(), и избежать проблемы
     *  затирания типов?
     */

    //todo: String response = RequestInvoker.invoke() ??
    String response = requestInvoker.invoke(String.class)
      .setPrivate(true)
      .setName("Петя")
      .setBin("бин выставлен вручную")
      .send();

    log.info("response is {}", response);

  }
}
