package com.blunt.sms.controller;

import com.blunt.sms.dto.MessageDto;
import com.blunt.sms.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sms")
public class SmsController {

  private final SmsService smsService;

  @GetMapping("test")
  public String testSmsService(){
    return "success";
  }

  @PostMapping("wapp")
  public ResponseEntity<Object> sendWhatappSMS(@RequestBody MessageDto messageDto) {
    return smsService.sendWhatsappMessage(messageDto);
  }

  @PostMapping("text")
  public ResponseEntity<Object> sendTextSMS(@RequestBody MessageDto messageDto) {
    return smsService.sendTextMessage(messageDto);
  }
}
