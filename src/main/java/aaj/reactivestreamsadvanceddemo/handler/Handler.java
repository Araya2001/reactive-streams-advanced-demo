package aaj.reactivestreamsadvanceddemo.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface Handler {
  Mono<ServerResponse> postEmitNext(ServerRequest request);

  Mono<ServerResponse> postEmitComplete(ServerRequest request);

  Mono<ServerResponse> getStream(ServerRequest request);

  Mono<ServerResponse> postNewSink(ServerRequest request);
}
