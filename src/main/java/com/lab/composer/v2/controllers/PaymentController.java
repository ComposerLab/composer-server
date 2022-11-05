package com.lab.composer.v2.controllers;

import com.lab.composer.v2.common.utils.DataResponse;
import com.lab.composer.v2.controllers.dto.LookDto;
import com.lab.composer.v2.controllers.dto.LookDto.LookSimple;
import com.lab.composer.v2.controllers.dto.MemberDto;
import com.lab.composer.v2.controllers.dto.MemberResponseDto;
import com.lab.composer.v2.controllers.dto.PaymentDto;
import com.lab.composer.v2.controllers.dto.PaymentDto.PaymentRequestDto;
import com.lab.composer.v2.controllers.dto.PaymentDto.PaymentResHandleDto;
import com.lab.composer.v2.controllers.dto.PaymentDto.PaymentResHandleFailDto;
import com.lab.composer.v2.controllers.dto.PaymentDto.PaymentResponseDto;
import com.lab.composer.v2.controllers.dto.PaymentDto.PaymentSimple;
import com.lab.composer.v2.domain.LookAround;
import com.lab.composer.v2.domain.Member;
import com.lab.composer.v2.domain.Payment;
import com.lab.composer.v2.services.LookService;
import com.lab.composer.v2.services.MemberService;
import com.lab.composer.v2.services.PaymentService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
  private final PaymentService paymentService;
  private final MemberService memberService;
  private final LookService lookService;

  @PostMapping("/info")
  public PaymentResponseDto requestPayments(@RequestBody PaymentRequestDto paymentRequestDto) {
    MemberResponseDto memberResponseDto = memberService.getMyInfo();
    MemberDto memberDto = memberService.getMember(memberResponseDto.getEmail());
    Member member = memberDto.toEntity();

    System.out.println("paymentRequestDto : " + paymentRequestDto.getLookAroundId());

    LookSimple lookDetail = lookService.getDetailInfo(Integer.toString(paymentRequestDto.getLookAroundId())).map(LookDto.LookSimple::new).get();
    LookAround lookAround = lookDetail.toEntity();

    paymentRequestDto.setMember(member);
    paymentRequestDto.setLookAround(lookAround);

    return paymentService.requestPayments(paymentRequestDto);
  }

  @GetMapping("/success")
  public PaymentResHandleDto requestFinalPayments(@RequestParam String paymentKey, @RequestParam String orderId, @RequestParam Long amount) {
    System.out.println("orderId : " + orderId);

    paymentService.verifyRequest(paymentKey, orderId, amount);

    PaymentResHandleDto result = paymentService.requestFinalPayment(paymentKey, orderId, amount);

    return result;
  }

  @GetMapping("/fail")
  public PaymentResHandleFailDto requestFail(@RequestParam(name = "code") String errorCode, @RequestParam(name = "message") String errorMsg, @RequestParam(name = "orderId") String orderId) {
    return paymentService.requestFail(errorCode, errorMsg, orderId);
  }

  @GetMapping("/latest")
  public DataResponse<List<PaymentSimple>> latest() {
    MemberResponseDto memberResponseDto = memberService.getMyInfo();
    MemberDto memberDto = memberService.getMember(memberResponseDto.getEmail());
    Member member = memberDto.toEntity();

    List<PaymentSimple> paymentResponseDtoList = paymentService.getLatest(member);

    return new DataResponse<>(paymentResponseDtoList);
  }

  @GetMapping("/mine")
  public DataResponse<List<PaymentSimple>> getMyPayment() {
    MemberResponseDto memberResponseDto = memberService.getMyInfo();
    MemberDto memberDto = memberService.getMember(memberResponseDto.getEmail());
    Member member = memberDto.toEntity();

    List<PaymentSimple> paymentResponseDtoList = paymentService.getMyPayment(member);

    return new DataResponse<>(paymentResponseDtoList);
  }
}
