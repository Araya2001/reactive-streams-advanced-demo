package aaj.reactivestreamsadvanceddemo.router;

import aaj.reactivestreamsadvanceddemo.handler.Handler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration(proxyBeanMethods = false)
public class Router {
  @Bean
  public RouterFunction<ServerResponse> route(Handler handler) {
    return RouterFunctions
        .route(POST("/reactive-streams-advanced-demo/api/v1/emit/next"), handler::postEmitNext)
        .andRoute(POST("/reactive-streams-advanced-demo/api/v1/emit/complete"), handler::postEmitComplete)
        .andRoute(POST("/reactive-streams-advanced-demo/api/v1/sink/new"), handler::postNewSink)
        .andRoute(GET("/reactive-streams-advanced-demo/api/v1/stream"), handler::getStream)
        ;
  }
}
