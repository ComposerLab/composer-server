package com.lab.composer.v2.domain;

import com.lab.composer.v2.controllers.dto.CSDto.CSResponseDto;
import com.lab.composer.v2.controllers.dto.PaymentDto.PaymentResponseDto;
import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CS {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  @NotNull
  private Long id;

  @Column(name = "email")
  private String email;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Member member;

  public CSResponseDto toDto() {
    return CSResponseDto.builder()
        .email(email)
        .title(title)
        .content(content)
        .build();
  }
}
