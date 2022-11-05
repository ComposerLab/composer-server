package com.lab.composer.v2.domain;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @NotNull
  private Long id;

  @NotNull
  private String email;

  @NotNull
  private String pwd;

  @NotNull
  private String name;

  @NotNull
  private String phoneNumber;

  @Enumerated(EnumType.STRING)
  private Roles roles;

  @Builder
  public Member(Long id, String email, String pwd, String name, String phoneNumber, Roles roles) {
    this.id = id;
    this.email = email;
    this.pwd = pwd;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.roles = roles;
  }

  public enum Roles {
    USER, SELLER, ADMIN
  }
}