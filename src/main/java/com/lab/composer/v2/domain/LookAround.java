package com.lab.composer.v2.domain;

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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "look_around")
@Entity
public class LookAround {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  @NotNull
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "artist")
  private String artist;

  @Column(name = "cost")
  private String cost;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Member member;

  @Column(name = "music")
  private String music;

  @Column(name = "image")
  private String image;

  @Builder
  public LookAround(Long id, String title, String cost, String artist, Member member, String music, String image) {
    this.id = id;
    this.title = title;
    this.cost = cost;
    this.artist = artist;
    this.member = member;
    this.music = music;
    this.image = image;
  }
}
