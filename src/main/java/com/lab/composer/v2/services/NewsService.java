package com.lab.composer.v2.services;

import com.lab.composer.v2.controllers.dto.NewsDto;
import com.lab.composer.v2.repositories.NewsRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService {
  private final NewsRepository newsRepository;

  public List<NewsDto.NewsSimple> getLatest() {
    return newsRepository.findAll().stream().map(NewsDto.NewsSimple::new).collect(
        Collectors.toList());
  }
}
