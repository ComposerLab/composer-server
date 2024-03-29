package com.lab.composer.v2.controllers;

import com.lab.composer.v2.controllers.dto.MemberDto;
import com.lab.composer.v2.controllers.dto.MemberResponseDto;
import com.lab.composer.v2.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
  private final MemberService memberService;

  @GetMapping("/info")
  public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
    return ResponseEntity.ok(memberService.getMyInfo());
  }

  @GetMapping("/{email}")
  public ResponseEntity<MemberResponseDto> getMemberInfo(@PathVariable String email) {
    return ResponseEntity.ok(memberService.getMemberInfo(email));
  }
}