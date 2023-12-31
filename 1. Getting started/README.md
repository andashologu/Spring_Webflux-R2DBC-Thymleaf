# Getting Started

- Head over to the Spring Initializr website.
    Project type: Maven 
    Language: Java
    Packaging: Jar
    Java version: Recommended
- Add dependency “Spring Reactive Web”; Netty will be used (default) for our projects
Generate Zip

- Open the Unzipped folder on VS Code

# Spring-Webflux

Spring WebFlux supports two programming models:

1. Annotation-based: similar to the traditional Spring MVC framework, this model uses annotations to map requests to handler methods.
    Create a controller class:

        @RestController
        public class WebFluxController {
            @GetMapping("/mono")
            public Mono<String> getMonoResult() {
                return Mono.just("Result from Mono");
            }

            @GetMapping("/flux")
            public Flux<String> getFluxResult() {
                return Flux.just("Result from Flux");
            }
        }
        
        To handle non-blocking requests in Spring, you can use the @Async annotation to annotate methods that should be executed asynchronously, and use the TaskExecutor interface to control the execution of asynchronous tasks.

        Or you can also use the Reactor (Mono, Flux) and Spring WebFlux.

        Flux and Mono:

        Spring Webflux uses Project Reactor as reactive library. Spring WebFlux heavily uses two publishers:

        Mono: Returns 0 or 1 element.
        Flux: Returns 0…N elements.

2. Functional: functional programming concepts, such as lambda expressions and functional interfaces, to define the routing and handling of requests.
    Create a controller class:
    
    @Configuration
    public class GreetingController {
        @Bean
        public RouterFunction<ServerResponse> routeHelloWorld() {
            return route(GET("/hello"),
                    request -> ServerResponse.ok()
                            .contentType(MediaType.TEXT_PLAIN)
                            .body(Mono.just("Hello, Reactive World!"), String.class));
        }
    }
