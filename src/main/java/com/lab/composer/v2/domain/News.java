package com.lab.composer.v2.domain;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "news")
@Entity
public class News {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  @NotNull
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "subTitle")
  private String subTitle;

  @Column(name = "content")
  private String content;

  @Column(name = "image")
  private String image;

}
