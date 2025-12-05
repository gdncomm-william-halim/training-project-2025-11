package com.example.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class JwtBlacklistGlobalFilter implements GlobalFilter, Ordered {

  private final ReactiveStringRedisTemplate redisTemplate;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();
    String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

    // if no bearer token, just continue (maybe public endpoint)
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return chain.filter(exchange);
    }

    String token = authHeader.substring(7);
    String redisKey = "blacklist:" + token;

    // reactive call to Redis
    return redisTemplate.hasKey(redisKey)
        .flatMap(isBlacklisted -> {
          if (Boolean.TRUE.equals(isBlacklisted)) {
            // return 401 from gateway
            var response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            byte[] bytes = "Token is invalid (Logged out)".getBytes(StandardCharsets.UTF_8);
            var buffer = response.bufferFactory().wrap(bytes);
            return response.writeWith(Mono.just(buffer));
          }
          // not blacklisted → continue the chain
          return chain.filter(exchange);
        });
  }

  @Override
  public int getOrder() {
    return -1; // run early
  }
}
