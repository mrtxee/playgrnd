package org.mrtxee.playgrnd.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RootController {
  @GetMapping("/")
  public String index() {
    return "index";
  }

}
