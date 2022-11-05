package com.lab.composer.v2.services;

import com.lab.composer.v2.common.security.SecurityUtil;
import com.lab.composer.v2.controllers.dto.MemberDto;
import com.lab.composer.v2.controllers.dto.MemberResponseDto;
import com.lab.composer.v2.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;

  @Transactional(readOnly = true)
  public MemberResponseDto getMemberInfo(String email) {
    return memberRepository.findByEmail(email)
        .map(MemberResponseDto::of)
        .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
  }

  // 현재 SecurityContext 에 있는 유저 정보 가져오기
  @Transactional(readOnly = true)
  public MemberResponseDto getMyInfo() {
    return memberRepository.findById(SecurityUtil.getCurrentMemberId())
        .map(MemberResponseDto::of)
        .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
  }

  @Transactional(readOnly = true)
  public MemberDto getMember(String email) {
    return memberRepository.findByEmail(email)
        .map(MemberDto::of)
        .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
  }
}
