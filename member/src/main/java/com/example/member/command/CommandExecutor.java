package com.example.member.command;

public interface CommandExecutor {
  <R, T> T execute(Class<? extends Command<R, T>> commandClass, R request);
}
