package com.example.member.command;


import com.example.member.command.model.CreateMemberCommandRequest;
import com.example.member.command.model.CreateMemberCommandResponse;
import com.example.member.command.model.GetMemberCommandRequest;
import com.example.member.command.model.GetMemberCommandResponse;
import com.example.member.entity.Member;
import com.example.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetMemberCommandImpl implements GetMemberCommand {

  private final MemberRepository memberRepository;

  @Override
  public GetMemberCommandResponse execute(GetMemberCommandRequest request) {

    final var member = Member.builder()
        .email(request.getEmail())
        .build();

    final var savedMember = memberRepository.save(member);

    return GetMemberCommandResponse.builder()
        .password(savedMember.getPassword())
        .name(savedMember.getName())
        .email(savedMember.getEmail())
        .build();
  }

}
