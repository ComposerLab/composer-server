package com.lab.composer.v2.controllers.dto;

import com.lab.composer.v2.domain.Member;
import com.lab.composer.v2.domain.Member.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

  private String email;
  private String password;
  private String name;
  private String phoneNumber;
  private Roles roles;

  public Member toMember(PasswordEncoder passwordEncoder) {
    return Member.builder()
        .email(email)
        .pwd(passwordEncoder.encode(password))
        .name(name)
        .phoneNumber(phoneNumber)
        .roles(roles)
        .build();
  }

  public UsernamePasswordAuthenticationToken toAuthentication() {
    return new UsernamePasswordAuthenticationToken(email, password);
  }

  public static MemberRequestDto of(Member member) {
    return new MemberRequestDto(member.getEmail(), member.getPwd() ,member.getName(), member.getPhoneNumber(), member.getRoles());
  }
}