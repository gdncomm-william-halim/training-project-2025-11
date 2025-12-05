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
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class LoginCommandImpl  implements LoginCommand {

  private final StringRedisTemplate stringRedisTemplate;
  private final MemberIntegration memberIntegration;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  private final RedisTemplate<Object, Object> redisTemplate;

//  @Override
//  public LoginCommandResponse execute(LoginCommandRequest request) {
//
//    MemberAuthResponse memberData = memberIntegration.findByEmail(request.getEmail());
//    if (memberData == null) throw new RuntimeException("User not found");
//
//    if (!passwordEncoder.matches(request.getPassword(), memberData.getPassword())) {
//      throw new RuntimeException("Invalid Password");
//    }
//
//    String token = jwtService.generateToken(memberData.getId(), memberData.getEmail());
//    redisTemplate.opsForValue().set("active_session:" + memberData.getId(), token, 1, TimeUnit.HOURS
//    );
//    return LoginCommandResponse.builder().accessToken(token).build();
//
//  }


  @Override
  public Mono<LoginCommandResponse> execute(LoginCommandRequest request) {

    return memberIntegration.findByEmail(request.getEmail())
        .switchIfEmpty(Mono.error(new RuntimeException("User not found")))
        .map(memberData -> {
          if (!passwordEncoder.matches(request.getPassword(), memberData.getPassword())) {
            throw new RuntimeException("Invalid Password");
          }

          String token = jwtService.generateToken(memberData.getId(), memberData.getEmail());
          return LoginCommandResponse.builder().accessToken(token).build();
        });
  }

}
