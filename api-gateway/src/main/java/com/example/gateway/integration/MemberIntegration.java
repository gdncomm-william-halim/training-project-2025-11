package com.example.gateway.integration;

import com.example.gateway.integration.model.MemberAuthResponse;
import reactor.core.publisher.Mono;

public interface MemberIntegration {
 Mono <MemberAuthResponse> findByEmail(String email);
}
