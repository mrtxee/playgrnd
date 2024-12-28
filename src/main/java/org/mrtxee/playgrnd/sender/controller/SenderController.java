package org.mrtxee.playgrnd.sender.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrtxee.playgrnd.sender.service.DataBin;
import org.mrtxee.playgrnd.sender.service.requestInvoker;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SenderController {
  private final requestInvoker requestInvoker;
  private final DataBin dataBin;

  @PostConstruct
  private void initialSend() {
    log.debug("initialSend");

    // todo:

    String o = requestInvoker.invoke(String.class)
      .setPrivate(true)
      .setName("Петя")
      .setBin("бин выставлен вручную")
      .send();
    log.info("response is {}", o);

  }
}
