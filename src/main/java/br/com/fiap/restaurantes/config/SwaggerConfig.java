package br.com.fiap.restaurantes.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public List<GroupedOpenApi> apis() {
        return Arrays.asList(
                GroupedOpenApi.builder()
                .group("reserva-api**")
                .pathsToMatch("/reservas/**")
                .build(),
                GroupedOpenApi.builder()
                        .group("avaliacao-api**")
                        .pathsToMatch("/avaliacao/**")
                        .build()
        );
    }

}
