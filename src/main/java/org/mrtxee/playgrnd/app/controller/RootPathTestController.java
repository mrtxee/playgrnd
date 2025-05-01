package org.mrtxee.playgrnd.app.controller;

import lombok.RequiredArgsConstructor;
import org.mrtxee.playgrnd.app.annotaion.ArtemNotated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping()
public class RootPathTestController {

  @GetMapping("/test")
  @ArtemNotated
  public String index() {
    return "RootPathTestController index";
  }

  @GetMapping("/test/all")
  public String all() {
    return "RootPathTestController all PAGE";
  }

}
