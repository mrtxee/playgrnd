package org.mrtxee.playgrnd.sender.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrtxee.playgrnd.sender.dto.ControllerResponseDto;
import org.mrtxee.playgrnd.sender.service.RequestInvoker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SenderController {
  private final RequestInvoker requestInvoker;

  @PostConstruct
  private void initialSend() {
    log.debug("initialSend");
    sendDemo();
  }

  @GetMapping("/sender")
  public ControllerResponseDto sendDemo() {
    String response = requestInvoker.<String>invoke()
      .setPrivate(true)
      .setName("Петя")
      .setBin("бин выставлен вручную")
      .send();

    log.info("response is:\n{}", response);
    return new ControllerResponseDto(response);
  }

}
