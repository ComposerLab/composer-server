package com.lab.composer.v2.controllers.dto;

import com.lab.composer.v2.domain.LookAround;
import com.lab.composer.v2.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

public class LookDto {
  @Getter
  @NoArgsConstructor
  public static class LookSimple {
    private Long id;
    private String artist;
    private String cost;
    private String image;
    private Member member;
    private String music;
    private String title;

    public LookSimple(LookAround look) {
      this.id = look.getId();
      this.artist = look.getArtist();
      this.cost = look.getCost();
      this.image = look.getImage();
      this.member = look.getMember();
      this.music = look.getMusic();
      this.title = look.getTitle();
    }

    public LookAround toEntity() {
      return LookAround.builder()
          .id(id)
          .title(title)
          .artist(artist)
          .cost(cost)
          .music(music)
          .image(image)
          .member(member)
          .build();
    }
  }

  @Getter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class LookRequestDTO {
    String title;
    String artist;
    String cost;
    MultipartFile image;
    MultipartFile music;
  }

  @Getter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class LookCalcDTO {
    String title;
    String artist;
    String cost;
    String image;
    String music;
    Member member;

    public LookAround toEntity() {
      return LookAround.builder()
          .title(title)
          .artist(artist)
          .cost(cost)
          .music(music)
          .image(image)
          .member(member)
          .build();
    }
  }
}