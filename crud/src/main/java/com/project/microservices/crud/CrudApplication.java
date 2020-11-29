package com.project.microservices.crud;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class CrudApplication {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  public static void main(String[] args) {
    SpringApplication.run(CrudApplication.class, args);
  }

}
