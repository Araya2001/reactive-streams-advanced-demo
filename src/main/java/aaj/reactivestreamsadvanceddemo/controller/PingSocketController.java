package aaj.reactivestreamsadvanceddemo.controller;

import aaj.reactivestreamsadvanceddemo.service.PingFeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class PingSocketController {
  private final PingFeederService pingFeederService;

  @Autowired
  public PingSocketController(PingFeederService pingFeederService) {
    this.pingFeederService = pingFeederService;
  }

  @MessageMapping("ping")
  public Flux<String> feedSocket() {
    return pingFeederService.doSinkToFlux();
  }
}
