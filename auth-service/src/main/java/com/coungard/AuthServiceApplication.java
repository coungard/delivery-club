package com.coungard;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "com.coungard")
@EnableEurekaClient
@Slf4j
public class AuthServiceApplication {

  @Value("${spring.datasource.url}")
  private String datasourceUrl;

  @PostConstruct
  public void showConfig() {
    log.info("datasource.url = {}", datasourceUrl);
  }

  public static void main(String[] args) {
    SpringApplication.run(AuthServiceApplication.class, args);
  }
}