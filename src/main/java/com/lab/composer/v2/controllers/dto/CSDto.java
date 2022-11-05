package com.lab.composer.v2.controllers.dto;

import com.lab.composer.v2.domain.CS;
import com.lab.composer.v2.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CSDto {
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CSRequestDto {

    private Long id;
    private String email;
    private String title;
    private String content;
    private Member member;

    public CS toEntity() {
      return CS.builder()
          .id(id)
          .email(email)
          .title(title)
          .content(content)
          .member(member)
          .build();
    }

  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CSResponseDto {

    private Long id;
    private String email;
    private String title;
    private String content;
    private Member member;

    public CS toEntity() {
      return CS.builder()
          .id(id)
          .email(email)
          .title(title)
          .content(content)
          .member(member)
          .build();
    }
  }
}
