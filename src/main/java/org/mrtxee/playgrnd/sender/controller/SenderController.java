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
    
    String response = requestInvoker.<String>invoke()
      .setPrivate(true)
      .setName("Петя")
      .setBin("бин выставлен вручную")
      .send();

    log.info("response is {}", response);

  }
}
