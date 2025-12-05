package com.example.gateway.command;

public interface Command<R, T> {

  T execute(R commandRequest);
}
