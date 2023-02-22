package com.coungard;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.coungard")
@EnableEurekaClient
@EnableFeignClients
@Slf4j
public class OrderServiceApplication {

  @Value("${spring.datasource.url}")
  private String datasourceUrl;

  @PostConstruct
  public void showConfig() {
    log.info("datasource.url = {}", datasourceUrl);
  }

  public static void main(String[] args) {
    SpringApplication.run(OrderServiceApplication.class, args);
  }
}