package restclientsandbox;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class RestClientSandboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestClientSandboxApplication.class, args);
    }

    // https://jsonplaceholder.typicode.com/todos

    @Bean
    RestClient restClient(RestClient.Builder builder) {
        return builder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }

    @Bean
    ApplicationRunner runner(RestClient restClient) {
        return args -> {
            Todo[] todos = restClient.get()
                    .uri("/todos")
                    .retrieve()
                    .body(Todo[].class);

            for (Todo todo : todos) {
                System.out.println(todo);
            }
        };
    }

    record Todo(
            Long id,
            Long userId,
            String title,
            Boolean completed
    ) {
    }
}
