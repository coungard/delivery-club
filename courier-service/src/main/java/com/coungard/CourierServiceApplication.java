package com.coungard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.coungard")
@EnableEurekaClient
@EnableFeignClients
public class CourierServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CourierServiceApplication.class, args);
  }
}