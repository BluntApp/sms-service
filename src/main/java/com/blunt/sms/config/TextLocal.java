package com.blunt.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "text-local")
@Data
@Component
public class TextLocal {
  private String apiKey;
  private String sender;
  private String url;
}
