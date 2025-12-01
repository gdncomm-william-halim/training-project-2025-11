package com.example.gateway.command;

import com.example.gateway.command.model.LoginCommandRequest;
import com.example.gateway.command.model.LoginCommandResponse;

public interface LoginCommand extends Command<LoginCommandRequest, LoginCommandResponse> {
}
