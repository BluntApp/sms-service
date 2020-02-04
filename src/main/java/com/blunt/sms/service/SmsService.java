package com.blunt.sms.service;

import com.blunt.sms.config.TwilioAccount;
import com.blunt.sms.dto.MessageDto;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmsService {

  private final TwilioAccount twilioAccount;

  public ResponseEntity<Object> sendWhatsappMessage(MessageDto messageDto) {
    Twilio.init(twilioAccount.getSid(), twilioAccount.getToken());
    Message message = Message.creator(
        new com.twilio.type.PhoneNumber("whatsapp:" + messageDto.getToMobile()),
        new com.twilio.type.PhoneNumber(twilioAccount.getMessageCenter()),
        messageDto.getMessageContext())
        .create();
    return new ResponseEntity<>(message, HttpStatus.OK);
  }
}
