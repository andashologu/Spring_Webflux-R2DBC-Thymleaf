package com.example.reactivewebapp.Repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.example.reactivewebapp.Model.PostEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostRepository extends R2dbcRepository<PostEntity, Long> {
    
    Flux<PostEntity> findByTitleContaining(String name);
    Mono<PostEntity> findById(Long id);
}