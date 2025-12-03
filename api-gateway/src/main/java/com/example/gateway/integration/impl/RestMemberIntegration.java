package com.example.gateway.integration.impl;


import com.example.gateway.command.model.GetMemberCommandRequest;
import com.example.gateway.integration.MemberIntegration;
import com.example.gateway.integration.model.MemberAuthResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestMemberIntegration implements MemberIntegration {

  private final RestTemplate restTemplate = new RestTemplate();
  private static final String MEMBER_URL = "http://localhost:8081/member/getmember";

  @Override
  public MemberAuthResponse findByEmail(String email) {

    var requestPayload = new GetMemberCommandRequest(email);

    try {
      return restTemplate.postForObject(MEMBER_URL, requestPayload, MemberAuthResponse.class
      );
    } catch (Exception e) {
      return null;
    }
  }
}