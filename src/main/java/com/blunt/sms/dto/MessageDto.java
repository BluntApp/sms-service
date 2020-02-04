package com.blunt.sms.dto;

import lombok.Data;

@Data
public class MessageDto {
  private String messageContext;
  private String toMobile;
}
