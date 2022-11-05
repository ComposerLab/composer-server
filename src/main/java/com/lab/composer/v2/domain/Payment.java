package com.lab.composer.v2.domain;

import com.lab.composer.v2.controllers.dto.PaymentDto.PaymentResponseDto;
import java.util.Date;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "seq", nullable = false, unique = true)
  private Long req;

  @Column(nullable = false)
  private String payType;

  @Column(nullable = false)
  private Long amount;

  @Column(nullable = false)
  private String orderId;

  @Column(nullable = false)
  private String orderName;

  @Column(nullable = false)
  private String customerEmail;

  @Column(nullable = false)
  private String customerName;

  @Column(nullable = false)
  @ColumnDefault("'N'")
  private String paySuccessYn;

  @Column(nullable = true)
  private String payFailReason;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "look_around_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private LookAround lookAround;

  @Column(name = "create_dt", nullable = false, updatable = false)
  @CreationTimestamp
  private Date createDt;

  public void updatePayment(String paySuccessYn) {
    this.paySuccessYn = paySuccessYn;
  }

  public void updatePaymentFail(String paySuccessYn, String payFailReason) {
    this.paySuccessYn = paySuccessYn;
    this.payFailReason = payFailReason;
  }

  public PaymentResponseDto toDto() {
    return PaymentResponseDto.builder()
        .payType(payType)
        .amount(amount)
        .orderId(orderId)
        .orderName(orderName)
        .customerEmail(customerEmail)
        .customerName(customerName)
        .paySuccessYn("Y")
        .build();
  }
}
