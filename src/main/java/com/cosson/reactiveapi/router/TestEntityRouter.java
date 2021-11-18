package com.cosson.reactiveapi.router;

import com.cosson.reactiveapi.handler.EntityHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TestEntityRouter {

    /*
     *  Entrypoint of the API call, behavior like Controller
     * */
    @Bean
    public RouterFunction<ServerResponse> routes(EntityHandler entityHandler) {
        return route(POST("/api/test"), entityHandler::create)
                .andRoute(GET("/api/test/{id}"), entityHandler::get);
    }
}
