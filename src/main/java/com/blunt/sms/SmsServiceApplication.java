package com.blunt.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class SmsServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SmsServiceApplication.class, args);
  }

}
