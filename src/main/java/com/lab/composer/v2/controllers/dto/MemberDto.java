package com.lab.composer.v2.controllers.dto;

import com.lab.composer.v2.domain.Artical;
import com.lab.composer.v2.domain.LookAround;
import com.lab.composer.v2.domain.Member;
import com.lab.composer.v2.domain.Member.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
  private Long id;
  private String email;
  private String name;
  private String phoneNumber;
  private Roles roles;

  public Member toEntity() {
    return Member.builder()
        .id(id)
        .email(email)
        .name(name)
        .phoneNumber(phoneNumber)
        .roles(roles)
        .build();
  }

  public static MemberDto of(Member member) {
    return new MemberDto(member.getId(), member.getEmail(), member.getName(),
        member.getPhoneNumber(), member.getRoles());
  }
}
