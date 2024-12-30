package org.mrtxee.playgrnd.sender.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrtxee.playgrnd.sender.service.RequestInvoker;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
@Slf4j
public class Controller {
  private final RequestInvoker requestInvoker;

  @PostConstruct
  private void initialSend() {
    log.debug("initialSend");
    
    String response = requestInvoker.<String>invoke()
      .setPrivate(true)
      .setName("Петя")
      .setBin("бин выставлен вручную")
      .send();

    log.info("response is:\n{}", response);

  }
}
