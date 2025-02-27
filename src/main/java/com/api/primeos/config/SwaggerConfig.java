package com.api.primeos.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PrimeOS API")
                        .version("v0.0.1")
                        .description("API para gerenciar funcionários, clientes e gerar ordens de serviços.")
                        .contact(new Contact()
                                .name("Gustavo Peçanha")
                                .email("gustavopecanhaa@outlook.com")
                                .url("https://github.com/gustavopecanhaa"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT"))
                )
                .components(new Components());
    }
}

