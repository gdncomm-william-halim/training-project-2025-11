package com.example.product.command;

public interface Command<R, T> {

  T execute(R commandRequest);
}
