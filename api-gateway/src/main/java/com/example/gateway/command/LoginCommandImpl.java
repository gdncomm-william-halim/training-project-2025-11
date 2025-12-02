package com.example.gateway.command;

import com.example.gateway.command.model.LoginCommandRequest;
import com.example.gateway.command.model.LoginCommandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginCommandImpl  implements LoginCommand {

  private final StringRedisTemplate stringRedisTemplate;

  @Override
  public LoginCommandResponse execute(LoginCommandRequest request) {

    return null;

  }


}
