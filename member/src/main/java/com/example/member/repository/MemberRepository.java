package com.example.member.repository;

import com.example.member.entity.Member;
import com.example.member.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, String> {
  List<Member> getMemberByEmail(String email);
  //@Query("SELECT r FROM Recipe r Where r.uniqueValue = ?1")
//List<Recipe> findByUniqueValue(String uniqueValue);

}
