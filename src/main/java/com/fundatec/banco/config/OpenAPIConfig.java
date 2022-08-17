package com.fundatec.banco.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("TrabalhoLP1")
                        .description("Api de banco")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://fundatec.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Wiki")
                        .url("https://wiki.org/docs"));
    }
}