package com.example.member.command;


import com.example.member.command.model.CreateMemberCommandRequest;
import com.example.member.command.model.CreateMemberCommandResponse;
import com.example.member.entity.Member;
import com.example.member.repository.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CreateMemberCommandImpl implements CreateMemberCommand {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;


  @Override
  public CreateMemberCommandResponse execute(CreateMemberCommandRequest request) {


    String encodedPassword = passwordEncoder.encode(request.getPassword());

    final var member = Member.builder()
        .name(request.getName())
        .email(request.getEmail())
        .password(encodedPassword) // Note:  encrypt this!
        .build();

    final var savedMember = memberRepository.save(member);

    return CreateMemberCommandResponse.builder()
        .name(savedMember.getName())
        .email(savedMember.getEmail())
        .build();
  }

}
