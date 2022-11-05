package com.lab.composer.v2.controllers;

import com.lab.composer.v2.common.utils.DataResponse;
import com.lab.composer.v2.controllers.dto.NewsDto;
import com.lab.composer.v2.controllers.dto.NewsDto.NewsSimple;
import com.lab.composer.v2.services.NewsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
  private final NewsService newsService;

  @GetMapping("/latest")
  public DataResponse<List<NewsSimple>> getLatest() {
    List<NewsDto.NewsSimple> articals = newsService.getLatest();

    return new DataResponse<>(articals);
  }
}
