# Getting Started
 
 Setup Environment: 

 - Download Maven, Source Zip Archive from https://maven.apache.org/download.cgi. Extract to the path: C:\Program Files
   Set up the Maven directory:
 - Go to the settings of your device to edit environment variables. Edit system varialbles. Variable name: MAVEN_HOME value: path to maven folder you extracted/copied to program files e.g., C:\Program Files\apache-maven-version
 - Add Maven bin directory to path. You can do this by selecting the 'path' variable under system variables and then clicking 'edit'. On the new window click New and then copy and paste this: %MAVEN_HOME%\bin
- Verify Maven is installed by heading over to the command prompt and typing 'mvn -version'. If you can see the version of maven installation was successful otherwise if not you will see this message: 'mvn' is not recognized as an internal or external command, operable program, or batch file.
  Set up Java directory:
- In the same way,  - Download and install the latest version of Java JDK from the Oracle website: https://www.oracle.com/java/technologies/downloads
 - Verify Java was installed on your PC by heading over to the command prompt and type 'java -version'.
 - Otherwise follow the same steps for Maven.
Download and install VS Code
 - On VS Code Extensions, search for Spring Boot Dashboard, and install the extension.
 - Restart VS Code upon the completion of the installation of the extension.

- Head over to the Spring Initializr website.
    Project type: Maven 
    Language: Java
    Packaging: Jar
    Java version: Recommended
- Add dependency “Spring Reactive Web”; Netty will be used (default) for our projects
Generate Zip
- Open the Unzipped folder on VS Code
- Run the Project using Spring Boot Dashboard Extension
- On a web browser navigate to localhost:8080. You should get an error page: Whitelabel Error Page

# Spring-Webflux

Spring WebFlux supports two programming models:

1. Annotation-based: similar to the traditional Spring MVC Framework, this model uses annotations to map requests to handler methods.
   
Controller class:

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
        
To handle non-blocking requests in Spring, you can use the @Async annotation to annotate methods that should be executed asynchronously and use the TaskExecutor interface to control the execution of asynchronous tasks.

You can also use the Reactor (Mono, Flux) and Spring WebFlux.

Flux and Mono:

Spring Webflux uses Project Reactor as a reactive library. Spring WebFlux heavily uses two publishers:

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

- Locate Starter Class under source directory java/com/your-package-name.

       import org.springframework.boot.SpringApplication;
       import org.springframework.boot.autoconfigure.SpringBootApplication;
     
       @SpringBootApplication
       public class Application {
         	public static void main(String[] args) {
         		    SpringApplication.run(Application.class, args);
         	}
      }
- In this directory create a folder called 'server' and one called 'web'. Web will used for Thymleaf, and the Server will be used for integrating with KMP and Thymleaf.
- Under Web create a folder called 'controller'. NB all folder names must be small cases and class names must start with Capital Letter for Each word.
- Under the Controller folder create a file and name HomeController and Copy and Paste the following:

       import org.springframework.web.bind.annotation.GetMapping;
       import org.springframework.web.bind.annotation.RestController;
       
       import reactor.core.publisher.Flux;
       import reactor.core.publisher.Mono;
       
       @RestController
       public class HomeController {
           @GetMapping("/mono")
           public Mono<String> getMonoResult() {
               return Mono.just("Result from Mono");
           }
       
           @GetMapping("/flux")
           public Flux<String> getFluxResult() {
               return Flux.just("Result from Flux");
           }
       }

  - Rerun the project.
  - On localhost:8080/mono and localhost:8080/flux you should get results
  
