package com.lab.composer.v2.controllers.dto;

import com.lab.composer.v2.domain.Artical;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticalDto {

  @Data
  @NoArgsConstructor
  public static class ArticalSimple {
    private Long id;
    private String title;
    private String subTitle;
    private String content;
    private String image;

    public ArticalSimple(Artical artical) {
      this.id = artical.getIdx();
      this.title = artical.getTitle();
      this.subTitle = artical.getSubTitle();
      this.content = artical.getContent();
      this.image = artical.getImage();
    }
  }
}
