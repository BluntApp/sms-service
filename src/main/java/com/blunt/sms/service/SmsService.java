package com.blunt.sms.service;

import com.blunt.sms.config.TextLocal;
import com.blunt.sms.config.TwilioAccount;
import com.blunt.sms.dto.MessageDto;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmsService {

  private final TwilioAccount twilioAccount;
  private final TextLocal textLocal;
  private final RestTemplate restTemplate;

  public ResponseEntity<Object> sendWhatsappMessage(MessageDto messageDto) {
    Twilio.init(twilioAccount.getSid(), twilioAccount.getToken());
    Message message = Message.creator(
        new com.twilio.type.PhoneNumber("whatsapp:" + messageDto.getToMobile()),
        new com.twilio.type.PhoneNumber(twilioAccount.getMessageCenter()),
        messageDto.getMessageContext())
        .create();
    return new ResponseEntity<>(message, HttpStatus.OK);
  }

  public ResponseEntity<Object> sendTextMessage(MessageDto messageDto) {
    String queryParameters = "?apikey={apiKey}&message={message}&sender={sender}&numbers={numbers}";
    Map<String, String> queryMap= new HashMap<>();
    queryMap.put("apiKey", textLocal.getApiKey());
    queryMap.put("sender", textLocal.getSender());
    queryMap.put("message", messageDto.getMessageContext());
    queryMap.put("numbers", messageDto.getToMobile());

    ResponseEntity<JSONObject> responseEntity = restTemplate
        .getForEntity(textLocal.getUrl()+queryParameters, JSONObject.class, queryMap );
    log.info("SMS sent Response: "+responseEntity.getBody());
    log.info("SMS sent Status: "+responseEntity.getBody().get("status"));
    if(responseEntity.getBody().get("status").equals("success")){
      return new ResponseEntity<>("SMS Sent successfully",  HttpStatus.OK);
    } else if(responseEntity.getBody().get("status").equals("failure")){
      return new ResponseEntity<>("Sending SMS Failure",  HttpStatus.BAD_REQUEST);
    } else{
      return new ResponseEntity<>("SMS Service UnAvailable",  HttpStatus.SERVICE_UNAVAILABLE);
    }

  }
}
