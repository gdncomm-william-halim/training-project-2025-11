package com.example.gateway.command;

import com.example.gateway.command.model.LoginCommandRequest;
import com.example.gateway.command.model.LoginCommandResponse;
import reactor.core.publisher.Mono;

public interface LoginCommand  {
  Mono<LoginCommandResponse> execute(LoginCommandRequest request);
}
