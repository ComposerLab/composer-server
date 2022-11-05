package com.lab.composer.v2.controllers.dto;

import com.lab.composer.v2.domain.Member;
import com.lab.composer.v2.domain.Member.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
  private String email;
  private Roles roles;
  private String name;

  public static MemberResponseDto of(Member member) {
    return new MemberResponseDto(member.getEmail(), member.getRoles(), member.getName());
  }
}