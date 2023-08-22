package com.example.reactivewebapp.Repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.example.reactivewebapp.Model.PostEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PostRepository extends R2dbcRepository<PostEntity, UUID> {
    
    public Flux<PostEntity> findByTitleContaining(String name);
    public Mono<PostEntity> findById(UUID id);
}