package org.mrtxee.playgrnd.app.controller;

import lombok.RequiredArgsConstructor;
import org.mrtxee.playgrnd.app.aspect.AxNotated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ax")
public class AxController {

  @GetMapping(produces = "text/plain")
  @AxNotated
  public String index() {
    return "Ax index";
  }

}
