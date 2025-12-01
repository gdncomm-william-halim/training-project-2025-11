package com.example.cart.command;

public interface Command<R, T> {

  T execute(R commandRequest);
}
