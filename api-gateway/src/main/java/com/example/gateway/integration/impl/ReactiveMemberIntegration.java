package com.example.gateway.integration.impl;


import com.example.gateway.command.model.GetMemberCommandRequest;
import com.example.gateway.integration.MemberIntegration;
import com.example.gateway.integration.model.MemberAuthResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ReactiveMemberIntegration implements MemberIntegration {

//  private final RestTemplate restTemplate = new RestTemplate();
//  private static final String MEMBER_URL = "http://localhost:8081/member/getmember";

  private final WebClient webClient;

  public ReactiveMemberIntegration(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
  }
  @Override
  public Mono<MemberAuthResponse> findByEmail(String email) {
    var requestPayload = new GetMemberCommandRequest(email);

    return webClient.post()
        .uri("/member/getmember")
        .bodyValue(requestPayload)
        .retrieve()
        .bodyToMono(MemberAuthResponse.class)
        .onErrorResume(e -> Mono.empty());
  }


//  @Override
//  public MemberAuthResponse findByEmail(String email) {
//
//    var requestPayload = new GetMemberCommandRequest(email);
//
//    try {
//      return restTemplate.postForObject(MEMBER_URL, requestPayload, MemberAuthResponse.class
//      );
//    } catch (Exception e) {
//      return null;
//    }
//  }
}