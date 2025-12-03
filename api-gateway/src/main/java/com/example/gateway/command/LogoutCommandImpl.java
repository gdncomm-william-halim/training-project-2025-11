package com.example.gateway.command;

import com.example.gateway.command.model.LoginCommandRequest;
import com.example.gateway.command.model.LoginCommandResponse;
import com.example.gateway.integration.MemberIntegration;
import com.example.gateway.integration.model.MemberAuthResponse;
import com.example.gateway.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class LogoutCommandImpl implements LogoutCommand {


  private final RedisTemplate<String, String> redisTemplate;
  private final JwtService jwtService;

  @Override
  public void execute(String token) {
    if (token.startsWith("Bearer ")) token = token.substring(7);


    Date expiration = jwtService.extractExpiration(token);
    long timeRemaining = expiration.getTime() - System.currentTimeMillis();

    if (timeRemaining > 0) {
      redisTemplate.delete(token);
      redisTemplate.opsForValue().set("blacklist:" + token, "logged_out", timeRemaining, TimeUnit.MILLISECONDS);
    }
  }


}
