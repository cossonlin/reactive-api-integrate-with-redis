package com.cosson.reactiveapi.repo;

import com.cosson.reactiveapi.entity.TestEntity;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class TestRepository {

    private final ReactiveRedisOperations<String, TestEntity> template;

    public TestRepository(ReactiveRedisOperations<String, TestEntity> template) {
        this.template = template;
    }

    public Mono<TestEntity> findById(String id) {
        return template.<String, TestEntity>opsForHash().get("test", id);
    }

    public Mono<TestEntity> save(TestEntity entity) {
        if (entity.getId() == null) {
            String id = UUID.randomUUID().toString();
            entity.setId(id);
        }
        return template.<String, TestEntity>opsForHash().put("test", entity.getId(), entity)
                .log()
                .map(p -> entity);

    }
}
