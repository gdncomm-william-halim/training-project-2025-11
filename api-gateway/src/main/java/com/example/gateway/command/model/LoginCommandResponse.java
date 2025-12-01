package com.example.gateway.command.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoginCommandResponse {
  private String accessToken;
  private  String name;
  private  String email;
}
