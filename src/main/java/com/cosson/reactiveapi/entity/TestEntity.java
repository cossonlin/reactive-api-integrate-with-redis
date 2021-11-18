package com.cosson.reactiveapi.entity;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestEntity {
    private String id;
    private Object value;
}
