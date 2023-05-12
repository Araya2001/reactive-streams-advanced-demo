package aaj.reactivestreamsadvanceddemo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
@Log4j2
public class PingFeederServiceImpl implements PingFeederService {
  private Sinks.Many<String> many = Sinks.many().multicast().onBackpressureBuffer();

  @Override
  public Mono<Boolean> doEmitNextToSink() {
    log.info("EMITTING PING TO SINK!!!");
    return Mono.justOrEmpty(many.tryEmitNext("PING FROM FEEDER SERVICE").isSuccess());
  }

  @Override
  public Mono<Boolean> doEmitCompleteToSink() {
    log.info("EMITTING COMPLETE TO SINK!!!");
    return Mono.justOrEmpty(many.tryEmitComplete().isSuccess());
  }

  @Override
  public Flux<String> doSinkToFlux() {
    log.info("MULTICAST MANY SINK TO FLUX!!!");
    return many.asFlux().defaultIfEmpty("NO DATA AVAILABLE TO STREAM").doOnSubscribe(subscription -> log.info("CURRENT SUBSCRIBER COUNT : " + (many.currentSubscriberCount() + 1)));
  }

  @Override
  public Mono<Boolean> doNewSink() {
    many = Sinks.many().multicast().onBackpressureBuffer();
    return Mono.just(Boolean.TRUE);
  }
}
