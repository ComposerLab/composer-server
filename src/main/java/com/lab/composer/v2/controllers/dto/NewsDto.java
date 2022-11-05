package com.lab.composer.v2.controllers.dto;

import com.lab.composer.v2.domain.Artical;
import com.lab.composer.v2.domain.News;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsDto {
  @Data
  @NoArgsConstructor
  public static class NewsSimple {
    private Long id;
    private String title;
    private String subTitle;
    private String content;
    private String image;

    public NewsSimple(News news) {
      this.id = news.getId();
      this.title = news.getTitle();
      this.subTitle = news.getSubTitle();
      this.content = news.getContent();
      this.image = news.getImage();
    }
  }
}
