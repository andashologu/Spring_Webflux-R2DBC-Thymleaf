package com.example.reactivewebapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.reactivewebapp.Model.PostEntity;
import com.example.reactivewebapp.Repository.PostRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class PostController {

  @Autowired
  PostRepository postRepository;

  @GetMapping("/posts")
  @ResponseStatus(HttpStatus.OK)
  public Flux<PostEntity> getAll() {
      return postRepository.findAll();
  }

  @GetMapping("/posts/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<PostEntity> getPost(@PathVariable("id") Long id) {
    return postRepository.findById(id);
  }

  @PostMapping("/add")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<PostEntity> createPost(@RequestBody PostEntity post) {
    return postRepository.save(post);
  }
}