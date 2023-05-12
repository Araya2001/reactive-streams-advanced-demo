package aaj.reactivestreamsadvanceddemo.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PingFeederService {
  Mono<Boolean> doEmitNextToSink();

  Mono<Boolean> doEmitCompleteToSink();

  Flux<String> doSinkToFlux();

  Mono<Boolean> doNewSink();
}
