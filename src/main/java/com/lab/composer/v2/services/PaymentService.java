package com.lab.composer.v2.services;

import com.lab.composer.v2.controllers.dto.PaymentDto;
import com.lab.composer.v2.controllers.dto.PaymentDto.PaymentRequestDto;
import com.lab.composer.v2.controllers.dto.PaymentDto.PaymentResHandleDto;
import com.lab.composer.v2.controllers.dto.PaymentDto.PaymentResHandleFailDto;
import com.lab.composer.v2.controllers.dto.PaymentDto.PaymentResponseDto;
import com.lab.composer.v2.controllers.dto.PaymentDto.PaymentSimple;
import com.lab.composer.v2.domain.Member;
import com.lab.composer.v2.domain.Payment;
import com.lab.composer.v2.repositories.MemberRepository;
import com.lab.composer.v2.repositories.PaymentRepository;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentService {
  private final PaymentRepository paymentRepository;
  private final MemberRepository memberRepository;

  @Value("${payments.toss.test_client_api_key}")
  private String testClientApiKey;

  @Value("${payments.toss.test_secret_api_key}")
  private String testSecretApiKey;

  @Value("${payments.toss.success_url}")
  private String successCallBackUrl;

  @Value("${payments.toss.fail_url}")
  private String failCallBackUrl;

  @Transactional
  public PaymentResponseDto requestPayments(PaymentRequestDto paymentRequestDto) {

    Long amount = paymentRequestDto.getAmount();
    String payType = paymentRequestDto.getPayType();
    String customerEmail = paymentRequestDto.getCustomerEmail();
    String orderName = paymentRequestDto.getOrderName();

    PaymentResponseDto paymentResponseDto;

    Payment payment = paymentRequestDto.toEntity();

    payment.updatePayment("N");

    memberRepository.findByEmail(customerEmail);

    paymentRepository.save(payment);

    paymentResponseDto = payment.toDto();
    paymentResponseDto.setSuccessUrl(successCallBackUrl);
    paymentResponseDto.setFailUrl(failCallBackUrl);

    return paymentResponseDto;
  }

  @Transactional
  public void verifyRequest(String paymentKey, String orderId, Long amount) {
    paymentRepository.findTop1ByOrderIdOrderByCreateDt(orderId);
  }

  @Transactional
  public PaymentResHandleDto requestFinalPayment(String paymentKey, String orderId, Long amount) {
    RestTemplate rest = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    String testSecretApiKeys = testSecretApiKey + ":";

    String encodeAuth = new String(Base64.getEncoder().encode(testSecretApiKeys.getBytes(
        StandardCharsets.UTF_8)));

    headers.setBasicAuth(encodeAuth);
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    Payment payment = paymentRepository.findTop1ByOrderIdOrderByCreateDt(orderId);
    payment.updatePayment("Y");

    paymentRepository.save(payment);

    JSONObject body = new JSONObject();
    body.put("paymentKey", paymentKey);
    body.put("orderId", orderId);
    body.put("amount", amount);

    return rest.postForEntity("https://api.tosspayments.com/v1/payments/confirm", new HttpEntity<>(body, headers),
        PaymentResHandleDto.class).getBody();
  }

  @Transactional
  public PaymentResHandleFailDto requestFail(String errorCode, String errorMsg, String orderId) {
    Payment payment = paymentRepository.findTop1ByOrderIdOrderByCreateDt(orderId);
    payment.updatePaymentFail("N", errorMsg);

    paymentRepository.save(payment);

    return PaymentResHandleFailDto.builder().orderId(orderId).errorCode(errorCode).errorMsg(errorMsg).build();
  }

  @Transactional
  public List<PaymentDto.PaymentSimple> getLatest(Member member) {
    return paymentRepository.findAllByMember(member).stream().map(PaymentSimple::new).collect(Collectors.toList());
  }

  @Transactional
  public List<PaymentDto.PaymentSimple> getMyPayment(Member member) {
    return paymentRepository.findByMemberIdWithPaymentUsingJoin(member.getId()).stream().map(PaymentSimple::new).collect(Collectors.toList());
  }
}
