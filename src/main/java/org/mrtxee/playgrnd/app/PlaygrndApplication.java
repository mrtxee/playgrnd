package org.mrtxee.playgrnd.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.mrtxee.playgrnd.*"})
public class PlaygrndApplication {

  public static void main(String[] args) {
    SpringApplication.run(PlaygrndApplication.class, args);
  }

}
