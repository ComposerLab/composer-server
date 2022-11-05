package com.lab.composer.v2.controllers.dto;

import com.lab.composer.v2.domain.Member.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {

  private String grantType;
  private String accessToken;
  private String refreshToken;
  private Long accessTokenExpiresIn;
  private Roles roles;
}
