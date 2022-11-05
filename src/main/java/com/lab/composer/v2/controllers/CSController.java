package com.lab.composer.v2.controllers;

import com.lab.composer.v2.controllers.dto.CSDto.CSRequestDto;
import com.lab.composer.v2.controllers.dto.CSDto.CSResponseDto;
import com.lab.composer.v2.controllers.dto.MemberDto;
import com.lab.composer.v2.controllers.dto.MemberResponseDto;
import com.lab.composer.v2.controllers.dto.PaymentDto.PaymentRequestDto;
import com.lab.composer.v2.controllers.dto.PaymentDto.PaymentResponseDto;
import com.lab.composer.v2.domain.Member;
import com.lab.composer.v2.services.CSService;
import com.lab.composer.v2.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cs")
@RequiredArgsConstructor
public class CSController {
  private final MemberService memberService;
  private final CSService csService;

  @PostMapping("/update")
  public CSResponseDto postCs(@RequestBody CSRequestDto csRequestDto) {
    MemberResponseDto memberResponseDto = memberService.getMyInfo();
    MemberDto memberDto = memberService.getMember(memberResponseDto.getEmail());
    Member member = memberDto.toEntity();

    csRequestDto.setMember(member);

    return csService.update(csRequestDto);
  }
}
