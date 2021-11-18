package com.cosson.reactiveapi.handler;

import com.cosson.reactiveapi.repo.TestRepository;
import com.cosson.reactiveapi.entity.TestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

/*
 *  Business logic handler
 * */
@Component
public class EntityHandler {

    private final TestRepository testRepository;

    public EntityHandler(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Mono<ServerResponse> create(ServerRequest req) {
        return req.bodyToMono(TestEntity.class)
                .flatMap(this.testRepository::save)
                .flatMap(p -> ServerResponse.created(URI.create("/api/test/" + p.getId())).build());
    }

    public Mono<ServerResponse> get(ServerRequest req) {
        return this.testRepository.findById(req.pathVariable("id"))
                .flatMap(entity -> ServerResponse.ok().body(Mono.just(entity), TestEntity.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
