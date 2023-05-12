package aaj.reactivestreamsadvanceddemo.handler;

import aaj.reactivestreamsadvanceddemo.service.PingFeederService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class HandlerImpl implements Handler {
  private final PingFeederService pingFeederService;
  private final RSocketRequester rSocketRequester;

  @Autowired
  public HandlerImpl(PingFeederService pingFeederService, RSocketRequester rSocketRequester) {
    this.pingFeederService = pingFeederService;
    this.rSocketRequester = rSocketRequester;
  }

  @Override
  public Mono<ServerResponse> postEmitNext(ServerRequest request) {
    return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(pingFeederService.doEmitNextToSink().map(Object::toString), String.class);
  }

  @Override
  public Mono<ServerResponse> postEmitComplete(ServerRequest request) {
    return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(pingFeederService.doEmitCompleteToSink().map(Object::toString), String.class);
  }

  @Override
  public Mono<ServerResponse> getStream(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.TEXT_EVENT_STREAM)
        .body(rSocketRequester.route("ping").retrieveFlux(String.class), String.class);
  }

  @Override
  public Mono<ServerResponse> postNewSink(ServerRequest request) {
    return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(pingFeederService.doNewSink().map(Object::toString), String.class);
  }
}
