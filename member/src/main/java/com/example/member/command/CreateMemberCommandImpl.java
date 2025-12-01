package com.example.member.command;


import com.example.member.command.model.CreateMemberCommandRequest;
import com.example.member.command.model.CreateMemberCommandResponse;
import com.example.member.entity.Member;
import com.example.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CreateMemberCommandImpl implements CreateMemberCommand {

  private final MemberRepository memberRepository;

  @Override
  public CreateMemberCommandResponse execute(CreateMemberCommandRequest request) {

    final var member = Member.builder()
        .name(request.getName())
        .email(request.getEmail())
        .password(request.getPassword()) // Note: In real life, encrypt this!
        .build();

    final var savedMember = memberRepository.save(member);

    return CreateMemberCommandResponse.builder()
        .name(savedMember.getName())
        .email(savedMember.getEmail())
        .build();
  }

}
