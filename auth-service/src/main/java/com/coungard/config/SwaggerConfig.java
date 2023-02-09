package com.coungard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

  public static final String USER_TAG = "User Service";
  public static final String AUTH_TAG = "Auth Service";

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.coungard.controller"))
        .paths(PathSelectors.any())
        .build()
        .tags(
            new Tag(USER_TAG, "Operations related to user management"),
            new Tag(AUTH_TAG, "Operations related to authentication and registration")
        );
  }
}
