package com.example.member.command;


import com.example.member.command.model.CreateMemberCommandRequest;
import com.example.member.command.model.CreateMemberCommandResponse;
import com.example.member.command.model.GetMemberCommandRequest;
import com.example.member.command.model.GetMemberCommandResponse;

public interface GetMemberCommand extends Command<GetMemberCommandRequest, GetMemberCommandResponse> {
}
