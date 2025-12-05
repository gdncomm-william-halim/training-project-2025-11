package com.example.gateway.api;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiResponse<Object>> handleIllegalArgument(IllegalArgumentException ex) {
    log.warn("Bad request: {}", ex.getMessage(), ex);

    ApiResponse<Object> body = ApiResponse.error(ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
  }

  @ExceptionHandler(org.springframework.web.server.ResponseStatusException.class)
  public ResponseEntity<ApiResponse<Object>> handleResponseStatusException(
      org.springframework.web.server.ResponseStatusException ex,
      ServerWebExchange exchange
  ) {
    String path = exchange.getRequest().getPath().value();
    log.warn("ResponseStatusException at {}: status={}, reason={}",
        path, ex.getStatusCode(), ex.getReason(), ex);

    ApiResponse<Object> body = ApiResponse.error(
        ex.getReason() != null ? ex.getReason() : ex.getMessage()
    );
    return ResponseEntity.status(ex.getStatusCode()).body(body);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Object>> handleException(Exception ex, ServerWebExchange exchange) {
    String path = exchange.getRequest().getPath().value();
    log.error("Unhandled exception at path {}: {}", path, ex.getMessage(), ex);

    ApiResponse<Object> body = ApiResponse.error("Internal server error");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
  }
}
