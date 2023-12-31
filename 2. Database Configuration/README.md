# Connecting to MySQL Database

## Two ways to configure Repositories

    - R2dbcRepository: (Preffered)
        https://github.com/bezkoder/spring-boot-r2dbc-mysql-example
        https://github.com/hantsy/spring-r2dbc-sample/tree/master/data-r2dbc-repositories
    - DatabseClient:
        https://github.com/hantsy/spring-r2dbc-sample/tree/master/database-client

## Two ways to configure Controllers
    - Annotation-based: (Preffered)

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
        
    - Functional: 
    
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

## add the dependencoes

    - Spring Boot starter spring-boot-starter-data-r2dbc
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-r2dbc</artifactId>
        </dependency>

    - Add a r2dbc driver for the specific database you are using https://r2dbc.io/drivers/, mysql in this case
        <dependency>
			<groupId>com.github.jasync-sql</groupId>
			<artifactId>jasync-r2dbc-mysql</artifactId>
			<version>2.2.3</version>
		</dependency>

## Database Properties
    #spring.data.r2dbc.repositories.enabled=true #Enabled by default
    spring.r2dbc.url=r2dbc:<driver>://<host>:<port>/<database>
    spring.r2dbc.username=user
    spring.r2dbc.password=password

## Model
    @Data
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Table("tablename")
    public class Objectname {
        @Id
        @Column("id")
        private Integer id;

        @Column("variablename")
        private String variablename;
    }

## schema.sql
    Unlike JPA, Spring Data R2dbc does not maintain the database schemas, so they must be created manually. Spring Data R2dbc provides a ConnectionFactoryInitializer to allow execution of sql scripts on database when connected. In a Spring Boot application, it is configured for you automatically. When the application is starting up, it will scan schema.sql and data.sql files in the classpath to initialize the database.

    CREATE TABLE IF NOT EXISTS posts (id SERIAL PRIMARY KEY, variablename VARCHAR(255));

## On Start Application.java file:
    (a) Dont forget to Enable WebFlux by putting at @EnableWebFlux
        R2dbcRepositories are enabled by default, and so no need to include //@EnableR2dbcRepositories 
    (b) For database configuration, on this file you must initialise database 

        @Bean
        ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

            ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
            initializer.setConnectionFactory(connectionFactory);
            initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
            return initializer;
        }

