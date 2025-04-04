package siq.restaurantservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addParameters("restaurantId",
                                new io.swagger.v3.oas.models.parameters.PathParameter()
                                        .name("restaurantId")
                                        .description("Restaurant ID")
                                        .required(true)
                                        .example("1")))
                .info(new Info()
                        .title("Restaurant Service API")
                        .version("1.0")
                        .description("API for managing restaurants and their evaluations"));
    }
}