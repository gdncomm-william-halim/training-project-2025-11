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

    final var member = memberRepository.getMemberByEmail(request.getEmail());

    if (member.getFirst().getEmail().equals(request.getEmail()) && member.getFirst()
        .getPassword()
        .equals(request.getPassword())) {
      return GetMemberCommandResponse.builder().name(member.getFirst().getName()).email(member.getFirst().getEmail()).build();
    } else {

      return GetMemberCommandResponse.builder().email("error").name("error").build();
    }


  }

}
