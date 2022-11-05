package com.lab.composer.v2.controllers.dto;

import com.lab.composer.v2.domain.LookAround;
import com.lab.composer.v2.domain.Member;
import com.lab.composer.v2.domain.Payment;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PaymentDto {
  @Data
  @NoArgsConstructor
  public static class PaymentSimple {
    private String payType;
    private Long amount;
    private String orderName;
    private String customerEmail;
    private String customerName;
    private LookAround lookAround;
    private Date createDt;

    public PaymentSimple(Payment payment) {
      this.payType = payment.getPayType();
      this.amount = payment.getAmount();
      this.orderName = payment.getOrderName();
      this.customerEmail = payment.getCustomerEmail();
      this.customerName = payment.getCustomerName();
      this.lookAround = payment.getLookAround();
      this.createDt = payment.getCreateDt();
    }
  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class PaymentRequestDto {
    private String payType;
    private Long amount;
    private String orderName;
    private String customerEmail;
    private String customerName;
    private Member member;
    private int lookAroundId;
    private LookAround lookAround;

    public PaymentRequestDto(Payment payment) {
      this.payType = payment.getPayType();
      this.amount = payment.getAmount();
      this.orderName = payment.getOrderName();
      this.customerEmail = payment.getCustomerEmail();
      this.customerName = payment.getCustomerName();
    }

    public Payment toEntity() {
      return Payment.builder()
          .orderId(UUID.randomUUID().toString())
          .payType(payType)
          .amount(amount)
          .orderName(orderName)
          .customerEmail(customerEmail)
          .customerName(customerName)
          .member(member)
          .lookAround(lookAround)
          .build();
    }
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class PaymentResponseDto {
    private String payType;
    private Long amount;
    private String orderId;
    private String orderName;
    private String customerEmail;
    private String customerName;
    private String successUrl;
    private String failUrl;
    private String createDate;
    private String paySuccessYn;
    private String payFailReason;

    public PaymentResponseDto(Payment payment) {
      this.payType = payment.getPayType();
      this.amount = payment.getAmount();
      this.orderId = payment.getOrderId();
      this.orderName = payment.getOrderName();
      this.customerEmail = payment.getCustomerEmail();
      this.customerName = payment.getCustomerName();
    }

    public Payment toEntity() {
      return Payment.builder()
          .orderId(UUID.randomUUID().toString())
          .payType(payType)
          .amount(amount)
          .orderName(orderName)
          .customerEmail(customerEmail)
          .customerName(customerName)
          .paySuccessYn("Y")
          .payFailReason(payFailReason)
          .build();
    }
  }

  @Data
  public static class PaymentResHandleDto {
    String mId;
    String version;
    String paymentKey;
    String orderId;
    String orderName;
    String currency;
    String method;
    String totalAmount;
    String balanceAmount;
    String suppliedAmount;
    String vat;
    String status;
    String requestedAt;
    String approvedAt;
    String useEscrow;
    String cultureExpense;
    PaymentResHandleCardDto card;
    String type;
  }

  @Data
  public static class PaymentResHandleCardDto {
    String company;
    String number;
    String installmentPlanMonths;
    String isInterestFree;
    String approveNo;
    String useCardPoint;
    String cardType;
    String ownerType;
    String acquireStatus;
    String receiptUrl;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class PaymentResHandleFailDto {
    String errorCode;
    String errorMsg;
    String orderId;
  }

}
