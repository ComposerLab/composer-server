package com.lab.composer.v2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "refresh_token")
@Entity
public class RefreshToken {

  @Id
  @Column(name = "rt_key", updatable = true)
  private String key;

  @Column(name = "rt_value", updatable = true)
  private String value;

  @Builder
  public RefreshToken(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public RefreshToken updateValue(String token) {
    this.value = token;
    return this;
  }
}