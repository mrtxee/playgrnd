package org.mrtxee.playgrnd.app.controller;

import lombok.RequiredArgsConstructor;
import org.mrtxee.playgrnd.app.aspect.AxNotated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

  @GetMapping
  @AxNotated
  public String index() {
    return "TestController index";
  }

  @GetMapping("/all")
  public String all() {
    return "TestController all PAGE";
  }

}
