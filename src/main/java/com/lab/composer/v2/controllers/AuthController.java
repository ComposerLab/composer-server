package com.lab.composer.v2.controllers;

import com.lab.composer.v2.controllers.dto.MemberRequestDto;
import com.lab.composer.v2.controllers.dto.MemberResponseDto;
import com.lab.composer.v2.controllers.dto.TokenDto;
import com.lab.composer.v2.controllers.dto.TokenRequestDto;
import com.lab.composer.v2.services.AuthService;
import com.lab.composer.v2.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;
  private final MemberService memberService;

  @PostMapping("/signup")
  public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
    return ResponseEntity.ok(authService.signup(memberRequestDto));
  }

  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
    MemberResponseDto memberResponseDto = memberService.getMemberInfo(memberRequestDto.getEmail());

    return ResponseEntity.ok(authService.login(memberRequestDto, memberResponseDto.getRoles()));
  }

  @PostMapping("/reissue")
  public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
    return ResponseEntity.ok(authService.reissue(tokenRequestDto));
  }

//  @PostMapping("/find-password")
//  public ResponseEntity<MemberResponseDto> findPassword(@RequestBody MemberRequestDto memberRequestDto) {
//    return ResponseEntity.ok(authService.findPassword(memberRequestDto));
//  }
}