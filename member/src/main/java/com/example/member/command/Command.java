package com.example.member.command;

public interface Command<R, T> {

  T execute(R commandRequest);
}
