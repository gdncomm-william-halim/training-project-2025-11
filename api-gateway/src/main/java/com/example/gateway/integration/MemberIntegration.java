package com.example.gateway.integration;

import com.example.gateway.integration.model.MemberAuthResponse;

public interface MemberIntegration {
  MemberAuthResponse findByEmail(String email);
}
